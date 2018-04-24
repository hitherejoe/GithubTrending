package co.joebirch.mobile_ui.injection

import android.app.Application
import co.joebirch.mobile_ui.GithubTrendingApplication
import co.joebirch.mobile_ui.injection.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
        ApplicationModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: GithubTrendingApplication)

}