package co.joebirch.data.store

import co.joebirch.data.repository.ProjectsCache
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectsDataStoreFactoryTest {

    private val cache = mock<ProjectsCache>()
    private val cacheStore = mock<ProjectsCacheDataStore>()
    private val remoteStore = mock<ProjectsRemoteDataStore>()
    private val factory = ProjectsDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getRemoteStoreRetrievesRemoteSource() {
        assert(factory.getRemoteDataStore() is ProjectsRemoteDataStore)
    }

    @Test
    fun getCacheStoreRetrievesCacheSource() {
        assert(factory.getCacheDataStore() is ProjectsCacheDataStore)
    }

    @Test
    fun getDataStoreReturnsRemoteSourceWhenNoCachedData() {
        assert(factory.getDataStore(false, false) is ProjectsRemoteDataStore)
    }

    @Test
    fun getDataStoreReturnsRemoteSourceWhenCacheExpired() {
        assert(factory.getDataStore(false, false) is ProjectsRemoteDataStore)
    }

    @Test
    fun getDataStoreReturnsCacheSourceWhenDataIsCached() {
        stubProjectsCacheAreProjectsCached(true)
        stubProjectsCacheIsProjectsCachedExpired(false)

        assert(factory.getDataStore(true, false) is ProjectsCacheDataStore)
    }

    private fun stubProjectsCacheAreProjectsCached(areCached: Boolean) {
        whenever(cache.areProjectsCached())
                .thenReturn(Single.just(areCached))
    }

    private fun stubProjectsCacheIsProjectsCachedExpired(expired: Boolean) {
        whenever(cache.isProjectsCacheExpired())
                .thenReturn(Single.just(expired))
    }

}