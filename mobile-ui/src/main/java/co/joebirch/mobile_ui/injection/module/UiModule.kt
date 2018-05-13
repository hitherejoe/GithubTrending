package co.joebirch.mobile_ui.injection.module

import co.joebirch.domain.executor.PostExecutionThread
import co.joebirch.mobile_ui.UiThread
import co.joebirch.mobile_ui.bookmarked.BookmarkedActivity
import co.joebirch.mobile_ui.browse.BrowseActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

    @ContributesAndroidInjector
    abstract fun contributesBookmarkedActivity(): BookmarkedActivity
}