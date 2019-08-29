package ir.zngis.yaraapplication.repository.local.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ir.zngis.yaraapplication.repository.local.dao.MovieDao
import ir.zngis.yaraapplication.repository.local.dao.MovieDetailDao
import ir.zngis.yaraapplication.repository.local.dao.RatingDao
import ir.zngis.yaraapplication.repository.model.Movie
import ir.zngis.yaraapplication.repository.model.MovieDetail
import ir.zngis.yaraapplication.repository.model.Rating


@Database(entities = [Movie::class, MovieDetail::class, Rating::class], version = 1)
abstract class LocalDb : RoomDatabase() {


    abstract fun movieDao(): MovieDao

    abstract fun movieDetailDao(): MovieDetailDao

    abstract fun ratingDao(): RatingDao


    companion object {
        const val DB_NAME = "Db"
    }


}