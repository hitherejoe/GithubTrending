package co.joebirch.cache.test.factory

import co.joebirch.cache.model.CachedProject
import co.joebirch.data.model.ProjectEntity

object ProjectDataFactory {

    fun makeCachedProject(): CachedProject {
        return CachedProject(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                false)
    }

    fun makeBookmarkedCachedProject(): CachedProject {
        return CachedProject(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                true)
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomBoolean())
    }

    fun makeBookmarkedProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(),
                true)
    }
}