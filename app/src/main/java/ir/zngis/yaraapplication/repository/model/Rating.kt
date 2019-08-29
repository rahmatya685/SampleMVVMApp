package ir.zngis.yaraapplication.repository.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey


@Entity(
    tableName = "RATING", foreignKeys = [
        ForeignKey(
            entity = MovieDetail::class,
            parentColumns = ["IMDB_ID"],
            childColumns = ["FK_MOVIE_ID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.NO_ACTION
        )

    ]
)
class Rating : BaseClass() {

    @ColumnInfo(name = "FK_MOVIE_ID", typeAffinity = ColumnInfo.TEXT)
    var MovieId: String = ""

    @ColumnInfo(name = "SOURCE", typeAffinity = ColumnInfo.TEXT)
    var Source: String = ""


    @ColumnInfo(name = "VALUE", typeAffinity = ColumnInfo.TEXT)
    var Value: String = ""
}