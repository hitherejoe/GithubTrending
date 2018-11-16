package co.joebirch.domain.interactor.browse

import co.joebirch.domain.ObservableUseCase
import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.domain.model.Project
import co.joebirch.domain.repository.ProjectsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }

}