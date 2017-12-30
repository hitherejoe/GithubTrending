package co.joebirch.domain.interactor.bookmarked

import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.domain.interactor.SingleUseCase
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetBookmarkedProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<Project>, Nothing>(postExecutionThread) {

    public override fun buildUseCaseSingle(params: Nothing?): Single<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }
}