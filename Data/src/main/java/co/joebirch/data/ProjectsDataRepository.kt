package co.joebirch.data

import co.joebirch.data.mapper.ProjectMapper
import co.joebirch.data.store.ProjectsDataStoreFactory
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
        private val projectMapper: ProjectMapper,
        private val factory: ProjectsDataStoreFactory)
    : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bookmarkProject(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}