package co.joebirch.cache

import co.joebirch.cache.db.ProjectsDatabase
import co.joebirch.cache.mapper.CachedProjectMapper
import co.joebirch.cache.model.Config
import co.joebirch.data.model.ProjectEntity
import co.joebirch.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(
        private val projectsDatabase: ProjectsDatabase,
        private val mapper: CachedProjectMapper)
    : ProjectsCache {

    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projects.forEach {
                projectsDatabase.cachedProjectsDao().insertProject(
                        mapper.mapToCached(it))
            }
            Completable.complete()
        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsDatabase.cachedProjectsDao().getProjects()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
                .toObservable()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return Observable.defer {
            Observable.just(projectsDatabase.cachedProjectsDao().getBookmarkedProjects()
                    .map { mapper.mapFromCached(it) })
        }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Boolean {
        return true
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return currentTime - projectsDatabase.configDao().getConfig().lastCacheTime > expirationTime
    }
}