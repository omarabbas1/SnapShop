package com.myproject.project_279

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge

class CartFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart_fragment)

        // Set up button click listeners for each button in the navigation bar

        findViewById<ImageButton>(R.id.home_button).setOnClickListener {
            // Navigate to Home page
            startActivity(Intent(this, MainPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.favorite_button).setOnClickListener {
            // Navigate to Favorite page

        }

        findViewById<ImageButton>(R.id.scan_button).setOnClickListener {
            // Navigate to Scan page
            startActivity(Intent(this, ProductPageActivity::class.java))
        }



        findViewById<ImageButton>(R.id.profile_button).setOnClickListener {
            // Navigate to Profile page
            startActivity(Intent(this, ProfilePage::class.java))
        }

        // "Shop by Category" button listener
        findViewById<Button>(R.id.button_shop_by_category).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}
