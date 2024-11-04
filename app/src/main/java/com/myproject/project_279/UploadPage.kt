package com.myproject.project_279

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UploadPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_page)


        findViewById<ImageButton>(R.id.FavoriteButton).setOnClickListener {
            // Navigate to UploadPage
            val intent = Intent(this,FavoritesActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.homeButton).setOnClickListener {
            // Navigate to Home page
            startActivity(Intent(this, MainPageActivity::class.java))
        }



        findViewById<ImageButton>(R.id.ScanButton).setOnClickListener {
            // Navigate to Scan page
            startActivity(Intent(this, ProductPageActivity::class.java))
        }



        findViewById<ImageButton>(R.id.CartButton).setOnClickListener {
            // Navigate to Profile page
            startActivity(Intent(this, CartFragment::class.java))
        }

    }
}