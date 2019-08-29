package ir.zngis.yaraapplication.repository.local

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail

interface LocalRepository {

    fun handleMovies(movies: List<Movie>)

    fun getMovies():Single<List<Movie>>
    fun handleMoveDetail(t: MovieDetail)
    fun getMovieDetail(imdbId: String): Flowable<MovieDetail?>
}