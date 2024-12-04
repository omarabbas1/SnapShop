package com.myproject.project_279

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FavoritesActivity : AppCompatActivity() {

    private lateinit var favoritesListView: ListView
    private lateinit var emptyTextView: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)



        // Navigation buttons
        findViewById<ImageButton>(R.id.home_button).setOnClickListener {
            startActivity(Intent(this, MainPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.shop_button).setOnClickListener {
            startActivity(Intent(this, CartFragment::class.java))
        }

        findViewById<ImageButton>(R.id.scan_button).setOnClickListener {
            startActivity(Intent(this, ProductPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.profile_button).setOnClickListener {
            startActivity(Intent(this, ProfilePage::class.java))
        }


    }


}