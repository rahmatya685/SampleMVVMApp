package ir.zngis.yaraapplication.repository.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import ir.zngis.yaraapplication.repository.model.Movie


@Dao
interface MovieDao:BaseDao<Movie> {



    @Query("SELECT * FROM movie")
    fun getMovies():Single<List<Movie>>

}