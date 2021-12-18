package com.wohlmuth.onenote2021

import android.content.Context

class Preferences {

    companion object {
        const val PREFERENCES_USER_LOGGED_IN = "user_logged_in"
        const val PREFERENCES_NAME = "preferences_note"
    }
    fun setUserLoggedIn(context: Context, loggedIn: Boolean) {
        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        preferences.edit().putBoolean(PREFERENCES_USER_LOGGED_IN, loggedIn).apply()
    }

    fun isUserLoggedIn(context: Context): Boolean {
        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(PREFERENCES_USER_LOGGED_IN, false)
    }
}