package co.joebirch.domain.interactor.bookmark

import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.domain.CompletableUseCase
import co.joebirch.domain.repository.ProjectsRepository
import io.reactivex.Completable
import javax.inject.Inject

open class UnbookmarkProject @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : CompletableUseCase<UnbookmarkProject.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return projectsRepository.unbookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }

}