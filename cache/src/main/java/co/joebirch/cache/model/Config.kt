package co.joebirch.cache.model

import android.arch.persistence.room.Entity
import co.joebirch.cache.db.ConfigConstants

@Entity(tableName = ConfigConstants.TABLE_NAME)
class Config(val lastCacheTime: Long)