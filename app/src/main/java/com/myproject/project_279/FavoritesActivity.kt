package com.myproject.project_279

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritesActivity : AppCompatActivity() {

    private lateinit var favoritesRecyclerView: RecyclerView
    private lateinit var emptyImageView: View
    private lateinit var emptyTextView: TextView
    private lateinit var subTextView: TextView
    private lateinit var returnButton: Button
    private var favoriteItems = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        // Initialize views
        favoritesRecyclerView = findViewById(R.id.favorites_list)
        emptyImageView = findViewById(R.id.imageView)
        emptyTextView = findViewById(R.id.textView4)
        subTextView = findViewById(R.id.textView5)
        returnButton = findViewById(R.id.return_button)

        // Fetch favorites from the FavoritesHelper
        favoriteItems = FavoritesHelper.getFavorites(this).toMutableList()

        // Set up RecyclerView with the favorite items
        val adapter = FavoritesAdapter(favoriteItems, this) { item ->
            // Handle item removal
            removeItemFromFavorites(item)
        }
        favoritesRecyclerView.layoutManager = LinearLayoutManager(this)
        favoritesRecyclerView.adapter = adapter

        // Toggle visibility based on whether favorites exist
        if (favoriteItems.isNotEmpty()) {
            favoritesRecyclerView.visibility = View.VISIBLE
            emptyImageView.visibility = View.GONE
            emptyTextView.visibility = View.GONE
            subTextView.visibility = View.GONE
            returnButton.visibility = View.GONE
        } else {
            favoritesRecyclerView.visibility = View.GONE
            emptyImageView.visibility = View.VISIBLE
            emptyTextView.visibility = View.VISIBLE
            subTextView.visibility = View.VISIBLE
            returnButton.visibility = View.VISIBLE
        }

        // Return button click listener
        returnButton.setOnClickListener {
            startActivity(Intent(this, MainPageActivity::class.java))
        }

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

    private fun removeItemFromFavorites(item: Item) {
        // Remove the item from the favorites list and update UI
        FavoritesHelper.removeFavorite(this, item)
        favoriteItems.remove(item)
        (favoritesRecyclerView.adapter as FavoritesAdapter).notifyDataSetChanged()

        // Update visibility if no items are left
        if (favoriteItems.isEmpty()) {
            favoritesRecyclerView.visibility = View.GONE
            emptyImageView.visibility = View.VISIBLE
            emptyTextView.visibility = View.VISIBLE
            subTextView.visibility = View.VISIBLE
            returnButton.visibility = View.VISIBLE
        }
    }
}

