package co.joebirch.domain.interactor.browse

import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import co.joebirch.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
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
        stubProjectsRepositoryGetProjects(
                Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getProjects.buildUseCaseSingle().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsRepository() {
        stubProjectsRepositoryGetProjects(
                Observable.just(ProjectDataFactory.makeProjectList(2)))
        getProjects.buildUseCaseSingle().test()
        verify(projectsRepository).getProjects()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubProjectsRepositoryGetProjects(
                Observable.just(projects))
        val testObserver = getProjects.buildUseCaseSingle().test()
        testObserver.assertValue(projects)
    }

    private fun stubProjectsRepositoryGetProjects(single: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects())
                .thenReturn(single)
    }

}