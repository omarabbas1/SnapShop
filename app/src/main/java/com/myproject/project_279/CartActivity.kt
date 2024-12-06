package com.myproject.project_279

import android.os.Bundle
import android.content.Intent
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myproject.project_279.Item

class CartActivity : AppCompatActivity() {

    private lateinit var cartItems: ArrayList<Item> // List of items in the cart
    private lateinit var checkoutAdapter: CheckoutAdapter
    private lateinit var totalPriceTextView: TextView // TextView to display the total price

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart) // Ensure you're using the correct layout file

        totalPriceTextView = findViewById(R.id.total_amount) // TextView to display the total price

        // Retrieve cart items from the intent
        cartItems = intent.getParcelableArrayListExtra<Item>("cartItems") ?: arrayListOf() // Default to empty if null

        checkoutAdapter = CheckoutAdapter(cartItems, this, onQuantityChanged = {
            updateTotalPrice() // Recalculate total price when quantity changes
        })

        val recyclerView: RecyclerView = findViewById(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = checkoutAdapter

        // Other buttons (navigation buttons)
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

        updateTotalPrice() // Initial price calculation
    }

    // Method to update the total price
    private fun updateTotalPrice() {
        var total = 0.0
        for (item in cartItems) {
            total += item.price.toDouble() * item.quantity // Ensure both are numbers
        }
        totalPriceTextView.text = String.format("%.2f", total) // This will format the total price to two decimal places
    }
}
