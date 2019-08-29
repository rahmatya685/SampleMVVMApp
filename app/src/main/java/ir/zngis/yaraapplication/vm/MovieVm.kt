package ir.zngis.yaraapplication.vm

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail
import ir.zngis.yaraapplication.use_case.MovieManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MovieVm @Inject constructor(
    private var movieManager: MovieManager, application: Application
) : AndroidViewModel(application) {


    private val mMovies: MutableLiveData<List<Movie>> = MutableLiveData()

    private val mCurrentMovie: MutableLiveData<MovieDetail> = MutableLiveData()


    fun Movies(): LiveData<List<Movie>> = mMovies

    fun CurrentMovie(): LiveData<MovieDetail> = mCurrentMovie


    @SuppressLint("CheckResult")
    fun loadMovies() {
        movieManager.getMovies()
            .subscribe(
                {
                    mMovies.postValue(it)
                },
                {
                    it.printStackTrace()
                })
    }


    @SuppressLint("CheckResult")
    fun getMovieDetail(imdbId: String) {
        movieManager.getMovieDetail(imdbId)
            .subscribe(
                {
                    mCurrentMovie.postValue(it)
                },
                {
                    it.printStackTrace()
                })


    }


}