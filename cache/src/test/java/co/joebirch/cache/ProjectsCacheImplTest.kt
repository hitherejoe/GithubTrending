package co.joebirch.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import co.joebirch.cache.db.ProjectsDatabase
import co.joebirch.cache.mapper.CachedProjectMapper
import co.joebirch.cache.test.factory.ProjectDataFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ProjectsCacheImplTest {

    @Rule
    @JvmField var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            ProjectsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    private val entityMapper = CachedProjectMapper()
    private val cache = ProjectsCacheImpl(database, entityMapper)

    @Test
    fun clearTablesCompletes() {
        val testObserver = cache.clearProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveProjectsCompletes() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())

        val testObserver = cache.saveProjects(projects).test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCompletes() {
        val projects = listOf(ProjectDataFactory.makeProjectEntity())
        cache.saveProjects(projects).test().awaitTerminalEvent()

        val testObserver = cache.getProjects().test()
        testObserver.assertValue(projects)
    }
}