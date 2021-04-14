package com.fuze.ttapp.api.pref

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferencesImpl  @Inject constructor(context: Application) : AppPreferences {

    private val mPrefs: SharedPreferences = context.getSharedPreferences("App_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    }

    override var accessToken: String?
        get() = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
        set(accessToken) = mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
}
