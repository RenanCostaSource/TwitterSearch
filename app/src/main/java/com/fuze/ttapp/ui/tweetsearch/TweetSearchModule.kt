package com.fuze.ttapp.ui.tweetsearch

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class TweetSearchModule {

    @Provides
    fun provideViewModel(
            tweetSearchActivity: TweetSearchActivity , tweetSearchViewModelFactory: TweeterSearchViewModelFactory
    ) = ViewModelProvider(tweetSearchActivity, tweetSearchViewModelFactory).get(TweetSearchViewModel::class.java)
}
