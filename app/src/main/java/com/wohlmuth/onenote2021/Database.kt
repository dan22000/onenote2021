package com.wohlmuth.onenote2021

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        // Database properties
        private const val DATABASE_NAME = "onenote"
        private const val DATABASE_TABLE_NAME = "notes"
        private const val DATABASE_VERSION = 1

        // Database table column names
        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_MESSAGE = "message"
        private const val KEY_TIMESTAMP = "timestamp"

        // Database create table statement
        private const val CREATE_TABLE = ("""CREATE TABLE $DATABASE_TABLE_NAME(
            $KEY_ID INTEGER PRIMARY KEY,
            $KEY_TIMESTAMP INT,
            $KEY_TITLE TEXT,
            $KEY_MESSAGE TEXT
        )""")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
    }
}