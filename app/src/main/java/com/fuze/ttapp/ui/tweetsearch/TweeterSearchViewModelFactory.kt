package com.fuze.ttapp.ui.tweetsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fuze.ttapp.domain.TweetSearchUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TweeterSearchViewModelFactory @Inject constructor(
    private val tweeterSearchUseCase: TweetSearchUseCase
    ): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass : Class<T>): T {
        if (modelClass.isAssignableFrom(TweetSearchViewModel::class.java)) {
            return TweetSearchViewModel(tweeterSearchUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
