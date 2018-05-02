package co.joebirch.domain.interactor.browse

import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import co.joebirch.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun getProjectsCompletes() {
        stubProjectsRepositoryGetProjects(Single.just(
                ProjectDataFactory.makeProjectList(2)))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsRepository() {
        stubProjectsRepositoryGetProjects(Single.just(
                ProjectDataFactory.makeProjectList(2)))
        getProjects.buildUseCaseObservable().test()
        verify(projectsRepository).getProjects()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubProjectsRepositoryGetProjects(Single.just(projects))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stubProjectsRepositoryGetProjects(single: Single<List<Project>>) {
        whenever(projectsRepository.getProjects())
                .thenReturn(single.toObservable())
    }

}