package ir.zngis.yaraapplication.repository.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseClass : Parcelable {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    var Id: Int = 0

}