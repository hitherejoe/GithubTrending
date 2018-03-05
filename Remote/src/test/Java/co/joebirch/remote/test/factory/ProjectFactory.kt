package co.joebirch.remote.test.factory

import co.joebirch.remote.model.ProjectModel

object ProjectFactory {

    fun makeProjectModel(): ProjectModel {
        return ProjectModel(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomInt(), DataFactory.randomUuid(),
                OwnerFactory.makeOwnerModel())
    }

}