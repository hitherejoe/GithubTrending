package co.joebirch.mobile_ui.injection

import android.app.Application
import co.joebirch.mobile_ui.GithubTrendingApplication
import co.joebirch.mobile_ui.injection.module.ApplicationModule
import co.joebirch.mobile_ui.injection.module.CacheModule
import co.joebirch.mobile_ui.injection.module.DataModule
import co.joebirch.mobile_ui.injection.module.PresentationModule
import co.joebirch.mobile_ui.injection.module.RemoteModule
import co.joebirch.mobile_ui.injection.module.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
        ApplicationModule::class,
        UiModule::class,
        PresentationModule::class,
        DataModule::class,
        CacheModule::class,
        RemoteModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: GithubTrendingApplication)

}