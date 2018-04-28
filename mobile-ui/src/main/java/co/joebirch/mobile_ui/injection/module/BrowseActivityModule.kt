package co.joebirch.mobile_ui.injection.module

import co.joebirch.mobile_ui.browse.BrowseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BrowseActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributesBrowseActivity(): BrowseActivity

}