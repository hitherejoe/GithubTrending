package co.joebirch.data

import co.joebirch.data.mapper.ProjectMapper
import co.joebirch.data.store.ProjectsDataStoreFactory
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val factory: ProjectsDataStoreFactory)
    : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return factory.getDataStore().getProjects()
                .map { it.map { mapper.mapFromEntity(it) } }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
                .map { it.map { mapper.mapFromEntity(it) } }
    }
}