package com.wohlmuth.onenote2021

import android.content.Context

class Preferences {

    companion object {
        const val PREFERENCES_USER_LOGGED_IN = "user_logged_in"
        const val PREFERENCES_NOTE_TITLE = "note_title"
        const val PREFERENCES_NOTE_MESSAGE = "note_message"
        const val PREFERENCES_NAME = "preferences_note"
    }

    fun setNoteTitle(context: Context, noteTitle: String) {
        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        preferences.edit().putString(PREFERENCES_NOTE_TITLE, noteTitle).apply()
    }

    fun getNoteTitle(context: Context): String? {
        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return preferences.getString(PREFERENCES_NOTE_TITLE, null)
    }

    fun setNoteMessage(context: Context, noteMessage: String) {
        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        preferences.edit().putString(PREFERENCES_NOTE_MESSAGE, noteMessage).apply()
    }

    fun getNoteMessage(context: Context): String? {
        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return preferences.getString(PREFERENCES_NOTE_MESSAGE, null)
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