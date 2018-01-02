package co.joebirch.data.store

import co.joebirch.data.model.ProjectEntity
import co.joebirch.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Observable

class ProjectsCacheDataStore : ProjectsDataStore {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearProjects(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProjectBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProjectNotBookmarked(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}