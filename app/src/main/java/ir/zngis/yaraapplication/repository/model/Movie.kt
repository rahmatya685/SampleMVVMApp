package ir.zngis.yaraapplication.repository.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity


@Entity(
    tableName = "MOVIE"
)
open class Movie : BaseClass() {

    @ColumnInfo(name = "TITLE", typeAffinity = ColumnInfo.TEXT)
    var Title: String = ""


    @ColumnInfo(name = "YEAR", typeAffinity = ColumnInfo.TEXT)
    var Year: String = ""


    @ColumnInfo(name = "IMDB_ID", typeAffinity = ColumnInfo.TEXT)
    var imdbID: String = ""


    @ColumnInfo(name = "TYPE", typeAffinity = ColumnInfo.TEXT)
    var Type: String = ""


    @ColumnInfo(name = "POSTER", typeAffinity = ColumnInfo.TEXT)
    var Poster: String = ""

}