package co.joebirch.data.store

import co.joebirch.data.repository.ProjectsDataStore
import javax.inject.Inject

class ProjectsDataStoreFactory @Inject constructor(
        private val projectsCacheDataStore: ProjectsCacheDataStore,
        private val projectsRemoteDataStore: ProjectsRemoteDataStore) {

    fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }

    fun getRemoteDataStore(): ProjectsDataStore {
        return projectsRemoteDataStore
    }

}