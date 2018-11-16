package co.joebirch.mobile_ui.injection.module

import co.joebirch.data.repository.ProjectsRemote
import co.joebirch.mobile_ui.BuildConfig
import co.joebirch.remote.ProjectsRemoteImpl
import co.joebirch.remote.service.GithubTrendingService
import co.joebirch.remote.service.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}