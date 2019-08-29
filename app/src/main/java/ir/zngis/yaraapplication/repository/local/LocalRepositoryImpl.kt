package ir.zngis.yaraapplication.repository.local

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ir.zngis.yaraapplication.repository.local.dao.MovieDao
import ir.zngis.yaraapplication.repository.local.dao.MovieDetailDao
import ir.zngis.yaraapplication.repository.local.dao.RatingDao
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepositoryImpl @Inject constructor(
    var movieDao: MovieDao,
    var movieDetailDao: MovieDetailDao,
    var ratingDao: RatingDao
) : LocalRepository {


    override fun getMovies(): Single<List<Movie>> = movieDao.getMovies()

    override fun handleMovies(newMovies: List<Movie>) {

        val oldMovieIds = movieDao.getMovies().blockingGet().map { it.imdbID }

        val insertQueue = mutableListOf<Movie>()

        newMovies.forEach { newMovie ->
            if (oldMovieIds.count { it.contentEquals(newMovie.imdbID) } == 0)
                insertQueue.add(newMovie)
        }

        if (insertQueue.isNotEmpty())
            movieDao.insert(*insertQueue.toTypedArray())
    }

    override fun getMovieDetail(imdbId: String): Flowable<MovieDetail?> = movieDetailDao.getMovie(imdbId)

    override fun handleMoveDetail(newMovie: MovieDetail) {

        if (movieDetailDao.getMovieCount(newMovie.imdbID) == 0){
            movieDetailDao.insert(newMovie)
        }

    }
}