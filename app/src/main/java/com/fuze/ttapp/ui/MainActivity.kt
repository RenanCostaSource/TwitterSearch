package com.fuze.ttapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import dagger.android.AndroidInjection

abstract class MainActivity<out VM : BaseViewModel> : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            if (hasInjector())
                AndroidInjection.inject(this)
        }

        open fun hasInjector() = true

        abstract fun getViewModel(): VM


}
