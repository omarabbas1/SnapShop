package com.example.aubeventmanager

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.database.Cursor
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME}", null)

        val eventTextView = findViewById<TextView>(R.id.eventTextView)
        val stringBuilder = StringBuilder()

        if (cursor.moveToFirst()) {
            do {
                val eventNameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)
                val eventDescriptionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION)
                val eventSeatsIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SEATS)

                // Log column indices to help debug
                Log.d("DB_DEBUG", "Event Name Index: $eventNameIndex, Description Index: $eventDescriptionIndex, Seats Index: $eventSeatsIndex")

                if (eventNameIndex != -1 && eventDescriptionIndex != -1 && eventSeatsIndex != -1) {
                    val eventName = cursor.getString(eventNameIndex)
                    val eventDescription = cursor.getString(eventDescriptionIndex)
                    val eventSeats = cursor.getInt(eventSeatsIndex)

                    stringBuilder.append("Event: $eventName\nDescription: $eventDescription\nSeats: $eventSeats\n\n")
                }
            } while (cursor.moveToNext())
        }
        cursor.close()

        eventTextView.text = stringBuilder.toString()
    }
}
