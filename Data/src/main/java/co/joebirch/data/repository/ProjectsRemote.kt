package co.joebirch.data.repository

import co.joebirch.data.model.ProjectEntity
import io.reactivex.Flowable

interface ProjectsRemote {

    fun getProjects(): Flowable<List<ProjectEntity>>

}