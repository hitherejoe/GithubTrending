package co.joebirch.remote

import co.joebirch.data.model.ProjectEntity
import co.joebirch.data.repository.ProjectsRemote
import co.joebirch.remote.mapper.ProjectsResponseModelMapper
import co.joebirch.remote.service.GithubTrendingService
import io.reactivex.Flowable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
        private val service: GithubTrendingService,
        private val mapper: ProjectsResponseModelMapper)
    : ProjectsRemote {

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
                .map {
                    it.items.map { mapper.mapFromModel(it) }
                }
    }
}