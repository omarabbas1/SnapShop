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

    private lateinit var searchResultsRecyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchInput: EditText
    private lateinit var searchButton: Button

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
        searchResultsRecyclerView = findViewById(R.id.items_recycler_view)
        searchInput = findViewById(R.id.location_search)
        searchButton = findViewById(R.id.search_button)

        searchResultsRecyclerView.layoutManager = LinearLayoutManager(this)
        searchAdapter = SearchAdapter(listOf())  // Empty list initially
        searchResultsRecyclerView.adapter = searchAdapter

        searchButton.setOnClickListener {
            val query = searchInput.text.toString()
            if (query.isNotEmpty()) {
                searchItems(query)
            } else {
                Toast.makeText(this, "Please enter a search query", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchItems(query: String) {
        val call = ApiClient.retrofitService.searchItems(query)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                if (response.isSuccessful) {
                    val searchResponse = response.body()
                    if (searchResponse != null && searchResponse.items.isNotEmpty()) {
                        searchAdapter.updateItems(searchResponse.items)
                    } else {
                        Toast.makeText(this@CartActivity, "No items found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@CartActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Toast.makeText(this@CartActivity, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
