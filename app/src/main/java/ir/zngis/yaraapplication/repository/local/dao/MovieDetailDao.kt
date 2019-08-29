package ir.zngis.yaraapplication.repository.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import ir.zngis.yaraapplication.repository.model.MovieDetail


@Dao
interface MovieDetailDao:BaseDao<MovieDetail> {

    @Query("SELECT * FROM movie_detail WHERE movie_detail.IMDB_ID like :imdbId")
    fun getMovie(imdbId: String): Flowable<MovieDetail?>

    @Query("SELECT COUNT(*) FROM movie_detail WHERE movie_detail.IMDB_ID like :imdbId")
    fun getMovieCount(imdbId: String): Int
}