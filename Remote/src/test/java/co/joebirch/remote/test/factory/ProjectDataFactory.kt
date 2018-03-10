package co.joebirch.remote.test.factory

import co.joebirch.data.model.ProjectEntity
import co.joebirch.remote.model.OwnerModel
import co.joebirch.remote.model.ProjectModel
import co.joebirch.remote.model.ProjectsResponseModel

object ProjectDataFactory {

    fun makeOwner(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

    fun makeProject(): ProjectModel {
        return ProjectModel(DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomInt(),
                DataFactory.randomUuid(), makeOwner())
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid())
    }

    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }

}