package com.fuze.ttapp.di.builder

import com.fuze.ttapp.ui.tweetsearch.TweetSearchActivity
import com.fuze.ttapp.ui.tweetsearch.TweetSearchModule
import com.fuze.ttapp.ui.tweetsearch.UserProfileActivity
import com.fuze.ttapp.ui.tweetsearch.UserProfileModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(TweetSearchModule::class)])
    abstract fun bindTweetActivity(): TweetSearchActivity

    @ContributesAndroidInjector(modules = [(UserProfileModule::class)])
    abstract fun bindProfileActivity(): UserProfileActivity
}