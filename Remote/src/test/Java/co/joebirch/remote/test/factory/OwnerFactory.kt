package co.joebirch.remote.test.factory

import co.joebirch.remote.model.OwnerModel

object OwnerFactory {

    fun makeOwnerModel(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

}