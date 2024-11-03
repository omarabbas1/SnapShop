package com.example.aubeventmanager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "events.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Event"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_SEATS = "seats"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_SEATS + " INTEGER" + ")")
        db.execSQL(createTable)

        db.execSQL("INSERT INTO $TABLE_NAME VALUES ('Meeting', 'Meeting with the class', 30)")
        db.execSQL("INSERT INTO $TABLE_NAME VALUES ('Orientation', 'Orientation lectures', 15)")
        db.execSQL("INSERT INTO $TABLE_NAME VALUES ('Sport', 'Sports event afternoon', 150)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}
