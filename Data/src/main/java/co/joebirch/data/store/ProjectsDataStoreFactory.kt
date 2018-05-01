package co.joebirch.data.store

import co.joebirch.data.repository.ProjectsCache
import co.joebirch.data.repository.ProjectsDataStore
import javax.inject.Inject

class ProjectsDataStoreFactory @Inject constructor(
        private val projectsCache: ProjectsCache,
        private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    fun getDataStore(projectsCached: Boolean,
                     cacheExpired: Boolean): ProjectsDataStore {
        return if (projectsCached && !cacheExpired) {
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }

    fun getRemoteDataStore(): ProjectsDataStore {
        return projectsRemoteDataStore
    }

}