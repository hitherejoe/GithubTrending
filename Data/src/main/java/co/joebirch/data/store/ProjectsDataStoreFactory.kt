package co.joebirch.data.store

import co.joebirch.data.repository.ProjectsCache
import co.joebirch.data.repository.ProjectsDataStore
import javax.inject.Inject

open class ProjectsDataStoreFactory @Inject constructor(
        private val projectsCache: ProjectsCache,
        private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    open fun getDataStore(): ProjectsDataStore {
        return if (projectsCache.areProjectsCached() &&
                !projectsCache.isProjectsCacheExpired()) {
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }

    fun getRemoteDataStore(): ProjectsDataStore {
        return projectsRemoteDataStore
    }

}