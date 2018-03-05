package co.joebirch.cache

import co.joebirch.cache.db.ProjectsDatabase
import co.joebirch.cache.mapper.CachedProjectMapper
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
        return Observable.defer {
            Observable.just(projectsDatabase.cachedProjectsDao().getProjects()
                    .map { mapper.mapFromCached(it) })
        }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areProjectsCached(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isProjectsCacheExpired(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}