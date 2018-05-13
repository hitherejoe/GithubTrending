package co.joebirch.domain.repository

import co.joebirch.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface ProjectsRepository {

    fun getProjects(): Flowable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>

}