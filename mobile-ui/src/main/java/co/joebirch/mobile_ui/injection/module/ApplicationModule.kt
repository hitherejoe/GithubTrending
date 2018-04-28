package co.joebirch.mobile_ui.injection.module

import dagger.Module

@Module(includes = arrayOf(ViewModelModule::class,
        BrowseActivityModule::class))
class ApplicationModule {

}