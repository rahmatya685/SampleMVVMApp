package ir.zngis.yaraapplication.repository.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "MOVIE_DETAIL",
    indices = [Index(value = ["IMDB_ID"], name = "IMDB_ID_UNIQUE_CONSTRAINT_INDEX", unique = true)]
)
class MovieDetail : Movie() {

    @ColumnInfo(name = "RELEASED", typeAffinity = ColumnInfo.TEXT)
    var Released: String = ""


    @ColumnInfo(name = "RUNTIME", typeAffinity = ColumnInfo.TEXT)
    var Runtime: String = ""


    @ColumnInfo(name = "GENRE", typeAffinity = ColumnInfo.TEXT)
    var Genre: String = ""

    @ColumnInfo(name = "DIRECTOR", typeAffinity = ColumnInfo.TEXT)
    var Director: String = ""


    @ColumnInfo(name = "WRITER", typeAffinity = ColumnInfo.TEXT)
    var Writer: String = ""


    @ColumnInfo(name = "ACTORS", typeAffinity = ColumnInfo.TEXT)
    var Actors: String = ""


    @ColumnInfo(name = "AWARDS", typeAffinity = ColumnInfo.TEXT)
    var Awards: String = ""


    @ColumnInfo(name = "IMDB_RATING", typeAffinity = ColumnInfo.TEXT)
    var imdbRating: String = ""


    @ColumnInfo(name = "IMDB_VOTES", typeAffinity = ColumnInfo.TEXT)
    var imdbVotes: String = ""


    @ColumnInfo(name = "PRODUCTION", typeAffinity = ColumnInfo.TEXT)
    var Production: String = ""

    @ColumnInfo(name = "PLOT", typeAffinity = ColumnInfo.TEXT)
    var Plot: String = ""


    @Ignore
    var Ratings: List<Rating> = mutableListOf()

}