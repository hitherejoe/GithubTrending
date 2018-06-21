package co.joebirch.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import co.joebirch.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
data class Config(
        @PrimaryKey(autoGenerate = true)
        var id: Int = -1,
        var lastCacheTime: Long)