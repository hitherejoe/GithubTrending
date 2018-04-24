package co.joebirch.remote.mapper

import co.joebirch.data.model.ProjectEntity
import co.joebirch.remote.model.ProjectModel

class ProjectsResponseModelMapper: ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName, model.starCount.toString(),
                model.dateCreated, model.owner.ownerName, model.owner.ownerAvatar, true)
    }

}