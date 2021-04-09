package com.fuze.ttapp.ui.tweetsearch

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class UserProfileModule {

    @Provides
    fun provideViewModel(userProfileActivity: UserProfileActivity , userProfileViewModelFactory: UserProfileViewModelFactory) = ViewModelProvider(userProfileActivity, userProfileViewModelFactory).get(UserProfileViewModel::class.java)
}