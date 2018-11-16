package co.joebirch.mobile_ui.injection.module

import android.app.Application
import co.joebirch.cache.ProjectsCacheImpl
import co.joebirch.cache.db.ProjectsDatabase
import co.joebirch.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}