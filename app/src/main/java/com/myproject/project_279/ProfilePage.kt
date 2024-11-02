package com.myproject.project_279

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class ProfilePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilepage)

        // Set click listener for the edit profile button
        findViewById<Button>(R.id.editProfile).setOnClickListener {
            // Navigate to UploadPage
            val intent = Intent(this, UploadPage::class.java)
            startActivity(intent)
        }
    }
}
