package ir.zngis.yaraapplication.use_case

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail

interface MovieManager {

    fun getMovies():Flowable<List<Movie>>
    fun getMovieDetail(imdbId: String): Flowable<MovieDetail?>

}