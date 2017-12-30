package co.joebirch.domain.repository

import co.joebirch.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Single

interface ProjectsRepository {

    fun getProjects(): Single<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Single<List<Project>>

}