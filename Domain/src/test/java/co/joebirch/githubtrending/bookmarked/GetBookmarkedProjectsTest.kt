package co.joebirch.githubtrending.bookmarked

import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.domain.interactor.bookmarked.GetBookmarkedProjects
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import co.joebirch.githubtrending.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetBookmarkedProjectsTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubProjectsRepositoryGetBookmarkedProjects(
                Single.just(ProjectDataFactory.makeProjectList(2)))

        val testObserver = getBookmarkedProjects.buildUseCaseSingle().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkProjectsCallsRepository() {
        stubProjectsRepositoryGetBookmarkedProjects(
                Single.just(ProjectDataFactory.makeProjectList(2)))

        getBookmarkedProjects.buildUseCaseSingle().test()
        verify(projectsRepository).getBookmarkedProjects()
    }

    private fun stubProjectsRepositoryGetBookmarkedProjects(single: Single<List<Project>>) {
        whenever(projectsRepository.getBookmarkedProjects())
                .thenReturn(single)
    }

}