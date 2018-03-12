package co.joebirch.data

import co.joebirch.data.mapper.ProjectMapper
import co.joebirch.data.model.ProjectEntity
import co.joebirch.data.repository.ProjectsDataStore
import co.joebirch.data.store.ProjectsDataStoreFactory
import co.joebirch.data.test.factory.DataFactory
import co.joebirch.data.test.factory.ProjectFactory
import co.joebirch.domain.model.Project
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsDataRepositoryTest {

    private val mapper = mock<ProjectMapper>()
    private val factory = mock<ProjectsDataStoreFactory>()
    private val store = mock<ProjectsDataStore>()
    private val repository = ProjectsDataRepository(mapper, factory)

    @Before
    fun setup() {
        stubProjectsDataStoreFactoryGetDataStore()
    }

    @Test
    fun getProjectsCompletes() {
        stubProjectsDataStoreGetProjects(Observable.just(listOf(
                ProjectFactory.makeProjectEntity())))
        stubProjectMapperMapFromEntity(any(), ProjectFactory.makeProject())

        val testObserver = repository.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsDataStore() {
        stubProjectsDataStoreGetProjects(Observable.just(listOf(
                ProjectFactory.makeProjectEntity())))
        stubProjectMapperMapFromEntity(any(), ProjectFactory.makeProject())

        repository.getProjects().test()
        verify(store).getProjects()
    }

    @Test
    fun getProjectsReturnsData() {
        val response = listOf(ProjectFactory.makeProjectEntity())
        val data = listOf(ProjectFactory.makeProject())
        stubProjectsDataStoreGetProjects(Observable.just(response))
        stubProjectMapperMapFromEntity(response[0], data[0])

        val testObserver = repository.getProjects().test()
        testObserver.assertValue(data)
    }

    @Test
    fun bookmarkProjectCompletes() {
        stubProjectsDataStoreFactoryGetCacheDataStore()
        stubProjectsDataStoreBookmarkProject(Completable.complete())
        val testObserver = repository.bookmarkProject(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun bookmarkProjectCallsDataStore() {
        stubProjectsDataStoreFactoryGetCacheDataStore()
        val projectId = DataFactory.randomString()
        stubProjectsDataStoreBookmarkProject(Completable.complete())
        repository.bookmarkProject(projectId).test()
        verify(store).setProjectAsBookmarked(projectId)
    }

    @Test
    fun unbookmarkProjectCompletes() {
        stubProjectsDataStoreFactoryGetCacheDataStore()
        stubProjectsDataStoreUnBookmarkProject(Completable.complete())
        val testObserver = repository.unbookmarkProject(DataFactory.randomString()).test()
        testObserver.assertComplete()
    }

    @Test
    fun unbookmarkProjectCallsDataStore() {
        stubProjectsDataStoreFactoryGetCacheDataStore()
        val projectId = DataFactory.randomString()
        stubProjectsDataStoreUnBookmarkProject(Completable.complete())
        repository.unbookmarkProject(projectId).test()
        verify(store).setProjectAsNotBookmarked(projectId)
    }

    @Test
    fun getBookmarkedProjectsCompletes() {
        stubProjectsDataStoreFactoryGetCacheDataStore()
        stubProjectsDataStoreGetBookmarkedProjects(Observable.just(listOf(
                ProjectFactory.makeProjectEntity())))
        stubProjectMapperMapFromEntity(any(), ProjectFactory.makeProject())

        val testObserver = repository.getBookmarkedProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsCallsDataStore() {
        stubProjectsDataStoreFactoryGetCacheDataStore()
        stubProjectsDataStoreGetBookmarkedProjects(Observable.just(listOf(
                ProjectFactory.makeProjectEntity())))
        stubProjectMapperMapFromEntity(any(), ProjectFactory.makeProject())

        repository.getBookmarkedProjects().test()
        verify(store).getBookmarkedProjects()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        stubProjectsDataStoreFactoryGetCacheDataStore()
        val response = listOf(ProjectFactory.makeProjectEntity())
        val data = listOf(ProjectFactory.makeProject())
        stubProjectsDataStoreGetBookmarkedProjects(Observable.just(response))
        stubProjectMapperMapFromEntity(response[0], data[0])

        val testObserver = repository.getBookmarkedProjects().test()
        testObserver.assertValue(data)
    }

    private fun stubProjectsDataStoreFactoryGetDataStore() {
        whenever(factory.getDataStore())
                .thenReturn(store)
    }

    private fun stubProjectsDataStoreFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(store)
    }

    private fun stubProjectsDataStoreGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getProjects())
                .thenReturn(observable)
    }

    private fun stubProjectsDataStoreGetBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getBookmarkedProjects())
                .thenReturn(observable)
    }

    private fun stubProjectsDataStoreBookmarkProject(completable: Completable) {
        whenever(store.setProjectAsBookmarked(any()))
                .thenReturn(completable)
    }

    private fun stubProjectsDataStoreUnBookmarkProject(completable: Completable) {
        whenever(store.setProjectAsNotBookmarked(any()))
                .thenReturn(completable)
    }

    private fun stubProjectMapperMapFromEntity(entity: ProjectEntity,
                                               model: Project) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }
}