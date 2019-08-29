package ir.zngis.yaraapplication.repository.local.dao

import android.arch.persistence.room.Dao
import ir.zngis.yaraapplication.repository.model.Rating


@Dao
interface RatingDao : BaseDao<Rating> {
}