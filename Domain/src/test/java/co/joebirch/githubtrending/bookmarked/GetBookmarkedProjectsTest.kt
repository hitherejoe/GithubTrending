package co.joebirch.githubtrending.bookmarked

import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.domain.interactor.bookmark.GetBookmarkedProjects
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import co.joebirch.githubtrending.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
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
                Observable.just(ProjectDataFactory.makeProjectList(2)))

        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkProjectsCallsRepository() {
        stubProjectsRepositoryGetBookmarkedProjects(
                Observable.just(ProjectDataFactory.makeProjectList(2)))

        getBookmarkedProjects.buildUseCaseObservable().test()
        verify(projectsRepository).getBookmarkedProjects()
    }

    private fun stubProjectsRepositoryGetBookmarkedProjects(single: Observable<List<Project>>) {
        whenever(projectsRepository.getBookmarkedProjects())
                .thenReturn(single)
    }

}