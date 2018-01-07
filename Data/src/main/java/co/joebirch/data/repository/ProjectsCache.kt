package co.joebirch.data.repository

import co.joebirch.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsCache  {

    fun clearProjects(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

    fun areProjectsCached(): Boolean

    fun setLastCacheTime(lastCache: Long): Completable

    fun isProjectsCacheExpired(): Boolean

}