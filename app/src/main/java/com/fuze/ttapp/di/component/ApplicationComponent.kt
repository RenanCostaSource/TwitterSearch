package com.fuze.ttapp.di.component

import com.fuze.ttapp.TweetSearchApplication
import com.fuze.ttapp.di.builder.ActivityBuilder
import com.fuze.ttapp.di.module.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (ApplicationModule::class), (ActivityBuilder::class) ])
interface ApplicationComponent {

    fun inject(app: TweetSearchApplication)
}
