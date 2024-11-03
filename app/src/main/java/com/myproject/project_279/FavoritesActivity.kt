package com.myproject.project_279

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class FavoritesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        findViewById<ImageButton>(R.id.home_button).setOnClickListener {
            // Navigate to Home page
            startActivity(Intent(this, MainPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.shop_button).setOnClickListener {
            // Navigate to Favorite page
            startActivity(Intent(this, CartFragment::class.java))

        }

        findViewById<ImageButton>(R.id.scan_button).setOnClickListener {
            // Navigate to Scan page
            startActivity(Intent(this, ProductPageActivity::class.java))
        }



        findViewById<ImageButton>(R.id.profile_button).setOnClickListener {
            // Navigate to Profile page
            startActivity(Intent(this, ProfilePage::class.java))
        }

        findViewById<Button>(R.id.return_button).setOnClickListener {
            // Navigate to Favorite page
            startActivity(Intent(this, MainPageActivity::class.java))

        }





    }
    }



