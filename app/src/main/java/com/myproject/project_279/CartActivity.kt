package com.myproject.project_279

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.content.Intent
import android.widget.Button
import android.widget.ImageButton


class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart) // Use the layout resource directly

        // Find the Checkout button by its ID
        val checkoutButton = findViewById<Button>(R.id.checkout_button)
        checkoutButton.setOnClickListener {
            // Start OrderDetailActivity when Checkout button is clicked
            val intent = Intent(this, OrderDetailActivity::class.java)
            startActivity(intent)
        }
        findViewById<ImageButton>(R.id.home_button).setOnClickListener {
            // Navigate to Home page
            startActivity(Intent(this, MainPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.favorite_button).setOnClickListener {
            // Navigate to Favorite page
            startActivity(Intent(this, FavoritesActivity::class.java))

        }

        findViewById<ImageButton>(R.id.scan_button).setOnClickListener {
            // Navigate to Scan page
            startActivity(Intent(this, ProductPageActivity::class.java))
        }



        findViewById<ImageButton>(R.id.profile_button).setOnClickListener {
            // Navigate to Profile page
            startActivity(Intent(this, ProfilePage::class.java))
        }
    }
}

