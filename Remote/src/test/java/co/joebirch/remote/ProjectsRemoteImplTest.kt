package co.joebirch.remote

import co.joebirch.data.model.ProjectEntity
import co.joebirch.remote.mapper.ProjectsResponseModelMapper
import co.joebirch.remote.model.ProjectModel
import co.joebirch.remote.model.ProjectsResponseModel
import co.joebirch.remote.service.GithubTrendingService
import co.joebirch.remote.test.factory.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    private val service = mock<GithubTrendingService>()
    private val mapper = mock<ProjectsResponseModelMapper>()
    private val remote = ProjectsRemoteImpl(service, mapper)

    @Test
    fun getProjectsCompletes() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
                ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsService() {
        stubGithubTrendingServiceSearchRepositories(Observable.just(
                ProjectDataFactory.makeProjectsResponse()))
        stubProjectsResponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperMapFromModel(it, entity)
        }
        stubGithubTrendingServiceSearchRepositories(Observable.just(response))

        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    private fun stubGithubTrendingServiceSearchRepositories(observable:
                                                            Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
                .thenReturn(observable)
    }

    private fun stubProjectsResponseModelMapperMapFromModel(model: ProjectModel,
                                                            entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
                .thenReturn(entity)
    }

}