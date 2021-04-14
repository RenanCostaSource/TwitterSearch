package com.fuze.ttapp.di.module

import android.app.Application
import android.content.Context
import com.fuze.ttapp.api.pref.AppPreferences
import com.fuze.ttapp.api.pref.AppPreferencesImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(NetworkModule::class)])
class ApplicationModule (val app: Application){

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context =app

    @Provides
    @Singleton
    internal fun providePreferencesHelper(appPreferencesImpl: AppPreferencesImpl): AppPreferences = appPreferencesImpl
}
