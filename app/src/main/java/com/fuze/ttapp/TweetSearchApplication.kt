package com.fuze.ttapp

import android.app.Activity
import android.app.Application
import com.fuze.ttapp.di.component.ApplicationComponent
import com.fuze.ttapp.di.component.DaggerApplicationComponent
import com.fuze.ttapp.di.module.ApplicationModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class TweetSearchApplication : Application(),   HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)

        setupDefaultRxJavaErrorHandler()

    }

    private fun setupDefaultRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler { throwable ->
            throwable.printStackTrace()
        }
    }


    override fun activityInjector() = activityInjector



}