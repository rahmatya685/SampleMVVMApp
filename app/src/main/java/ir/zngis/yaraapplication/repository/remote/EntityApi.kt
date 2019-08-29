package ir.zngis.yaraapplication.repository.remote

import io.reactivex.Observable
import io.reactivex.Single
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail

interface EntityApi {


    fun getMovies(): Single<List<Movie>>

    fun getMovieDetail(imdbID: String): Single<MovieDetail>
}