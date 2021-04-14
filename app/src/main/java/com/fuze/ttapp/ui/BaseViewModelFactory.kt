package com.fuze.ttapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseViewModelFactory
@Inject constructor(): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
            return BaseViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
