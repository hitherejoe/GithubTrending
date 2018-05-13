package co.joebirch.data

import co.joebirch.data.mapper.ProjectMapper
import co.joebirch.data.repository.ProjectsCache
import co.joebirch.data.store.ProjectsDataStoreFactory
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val cache: ProjectsCache,
        private val factory: ProjectsDataStoreFactory)
    : ProjectsRepository {

    override fun getProjects(): Flowable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(),
                cache.isProjectsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { t1, t2 ->
                    Pair(t1, t2)
                })
                .toFlowable(BackpressureStrategy.LATEST)
                .flatMap {
                    factory.getDataStore(it.first, it.second).getProjects()
                }
                .flatMap { projects ->
                    factory.getCacheDataStore()
                            .saveProjects(projects)
                            .andThen(Flowable.just(projects.map { mapper.mapFromEntity(it) }))
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
                .map { it.map { mapper.mapFromEntity(it) } }
    }
}