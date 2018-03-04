package co.joebirch.remote.mapper

import co.joebirch.data.model.ProjectEntity
import co.joebirch.remote.model.ProjectModel

class ProjectsResponseModelMapper: ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity()
    }

}