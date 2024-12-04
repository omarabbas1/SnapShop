package com.myproject.project_279

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.myproject.project_279.Item

class CartActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val checkoutButton = findViewById<Button>(R.id.checkout_button)
        checkoutButton.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.home_button).setOnClickListener {
            startActivity(Intent(this, MainPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.favorite_button).setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }

        findViewById<ImageButton>(R.id.scan_button).setOnClickListener {
            startActivity(Intent(this, ProductPageActivity::class.java))
        }

        findViewById<ImageButton>(R.id.profile_button).setOnClickListener {
            startActivity(Intent(this, ProfilePage::class.java))
        }

        // Handle search functionality

    }



}
