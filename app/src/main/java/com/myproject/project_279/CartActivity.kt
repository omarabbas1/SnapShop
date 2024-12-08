package com.myproject.project_279

import android.os.Bundle
import android.content.Intent
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CartActivity : AppCompatActivity() {

    private lateinit var cartItems: ArrayList<Item>
    private lateinit var checkoutAdapter: CheckoutAdapter
    private lateinit var totalPriceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        totalPriceTextView = findViewById(R.id.total_amount)


        cartItems = intent.getParcelableArrayListExtra<Item>("cartItems") ?: arrayListOf()

        checkoutAdapter = CheckoutAdapter(cartItems, this, onQuantityChanged = {
            updateTotalPrice()
        })

        val recyclerView: RecyclerView = findViewById(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = checkoutAdapter

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

        updateTotalPrice()
    }


    private fun updateTotalPrice() {
        var total = 0.0
        for (item in cartItems) {
            total += item.price.toDouble() * item.quantity
        }
        totalPriceTextView.text = String.format("%.2f", total)
    }
}
