package co.joebirch.data.repository

import co.joebirch.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsDataStore {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun clearProjects(): Completable

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectBookmarked(projectId: String): Completable

    fun setProjectNotBookmarked(projectId: String): Completable

}