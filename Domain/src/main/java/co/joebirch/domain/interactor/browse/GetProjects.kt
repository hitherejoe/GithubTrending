package co.joebirch.domain.interactor.browse

import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.domain.interactor.FlowableUseCase
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : FlowableUseCase<List<Project>, Nothing>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Flowable<List<Project>> {
        return projectsRepository.getProjects()
    }

}