package co.joebirch.mobile_ui.injection.module

import co.joebirch.data.ProjectsDataRepository
import co.joebirch.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}