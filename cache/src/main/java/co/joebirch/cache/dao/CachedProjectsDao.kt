package co.joebirch.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import co.joebirch.cache.db.ProjectConstants.DELETE_PROJECTS
import co.joebirch.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import co.joebirch.cache.db.ProjectConstants.QUERY_PROJECTS
import co.joebirch.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import co.joebirch.cache.model.CachedProject

@Dao
abstract class CachedProjectsDao {

    @Query(QUERY_PROJECTS)
    abstract fun getProjects(): List<CachedProject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProject(cachedProject: CachedProject)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookmarkedProjects(): List<CachedProject>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookmarkStatus(isBookmarked: Boolean,
                                   projectId: String)

}