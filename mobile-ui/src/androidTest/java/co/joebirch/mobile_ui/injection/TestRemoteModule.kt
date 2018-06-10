package co.joebirch.mobile_ui.injection

import co.joebirch.data.repository.ProjectsRemote
import co.joebirch.remote.service.GithubTrendingService
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideGithubService(): GithubTrendingService {
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideProjectsRemote(): ProjectsRemote {
        return mock()
    }

}