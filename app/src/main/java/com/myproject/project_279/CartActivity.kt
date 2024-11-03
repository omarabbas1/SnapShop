package com.myproject.project_279

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.content.Intent
import android.widget.Button


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
    }
}

