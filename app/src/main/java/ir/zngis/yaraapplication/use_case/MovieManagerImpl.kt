package ir.zngis.yaraapplication.use_case

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ir.zngis.yaraapplication.repository.local.LocalRepository
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail
import ir.zngis.yaraapplication.repository.remote.EntityApi
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieManagerImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: EntityApi
) : MovieManager {


    @SuppressLint("CheckResult")
    override fun getMovies(): Flowable<List<Movie>> {
        val remoteData = remoteRepository.getMovies()
            .doOnSuccess { t: List<Movie>? ->
                t?.let {
                    localRepository.handleMovies(t)
                }
            }.doOnError {
                it?.printStackTrace()
            }

        val localData = localRepository.getMovies().subscribeOn(Schedulers.io())

        return Single.concat(localData, remoteData)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
    }


    override fun getMovieDetail(imdbId: String): Flowable<MovieDetail?> {

        val remoteData = remoteRepository.getMovieDetail(imdbId)
            .doOnSuccess { t: MovieDetail ->
                t.let {
                    localRepository.handleMoveDetail(t)
                }
            }.doOnError {
                it?.printStackTrace()
            }


        val localData = localRepository.getMovieDetail(imdbId)
            .doOnError { t: Throwable? ->
                t?.printStackTrace()
            }

        return Single.concat(remoteData.onErrorResumeNext { localData.firstOrError() }, localData.firstOrError())
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())


    }

}