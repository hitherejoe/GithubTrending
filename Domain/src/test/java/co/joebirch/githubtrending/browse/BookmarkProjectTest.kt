package co.joebirch.githubtrending.browse

import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.domain.interactor.browse.BookmarkProject
import co.joebirch.domain.repository.ProjectsRepository
import co.joebirch.githubtrending.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkProjectTest {

    private lateinit var bookmarkProject: BookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun bookmarkProjectCompletes() {
        stubProjectsRepositoryBookmarkProject(Completable.complete())

        val testObserver = bookmarkProject.buildUseCaseCompletable(
                BookmarkProject.Params(ProjectDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException() {
        bookmarkProject.buildUseCaseCompletable().test()
    }

    @Test
    fun bookmarkProjectRepository() {
        val projectId = ProjectDataFactory.randomUuid()
        stubProjectsRepositoryBookmarkProject(Completable.complete())

        bookmarkProject.buildUseCaseCompletable(BookmarkProject.Params(projectId)).test()
        verify(projectsRepository).bookmarkProject(projectId)
    }

    private fun stubProjectsRepositoryBookmarkProject(completable: Completable) {
        whenever(projectsRepository.bookmarkProject(any()))
                .thenReturn(completable)
    }

}