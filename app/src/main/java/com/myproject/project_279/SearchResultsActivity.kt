package com.myproject.project_279

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat




import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide
import okhttp3.*
import org.json.JSONObject
import java.io.IOException // This should not be manually added.


class SearchResultsActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        listView = findViewById(R.id.ItemsListView1)

        // Retrieve the search query from the intent
        val searchQuery = intent.getStringExtra("SEARCH_QUERY") ?: ""

        if (searchQuery.isNotEmpty()) {
            searchItems(searchQuery)
        } else {
            Toast.makeText(this, "Search query is empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchItems(query: String) {
        val request = Request.Builder()
            .url("http://10.0.2.2:8000/search/$query") // Correct search endpoint
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback { // OkHttp's callback
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@SearchResultsActivity, "Failed to load items", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.let { responseBody ->
                        try {
                            val jsonResponse = JSONObject(responseBody.string())
                            val itemsList = mutableListOf<Item>()

                            if (jsonResponse.has("items")) {
                                val items = jsonResponse.getJSONArray("items")
                                for (i in 0 until items.length()) {
                                    val item = items.getJSONObject(i)
                                    val itemName = item.optString("name", "Unknown") // Default values for safety
                                    val itemPrice = item.optString("price", "0")
                                    val itemImageUrl = item.optString("image_url", "")

                                    itemsList.add(Item(itemName, itemPrice, itemImageUrl))
                                }

                                // Update the ListView on the main thread
                                runOnUiThread {
                                    val adapter = SearchItemAdapter(this@SearchResultsActivity, itemsList)
                                    listView.adapter = adapter
                                }
                            } else {
                                runOnUiThread {
                                    Toast.makeText(this@SearchResultsActivity, "No items found", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            runOnUiThread {
                                Toast.makeText(this@SearchResultsActivity, "Error parsing response", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@SearchResultsActivity, "Request failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    // Item data class to store item details
    data class Item(val name: String, val price: String, val imageUrl: String)

    // Custom adapter for ListView
    inner class SearchItemAdapter(private val context: SearchResultsActivity, private val items: List<Item>) : BaseAdapter() {

        override fun getCount(): Int {
            return items.size
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
            val view = convertView ?: layoutInflater.inflate(R.layout.list_item_fashion, parent, false)

            val item = items[position]

            // Set the item details to the views
            val itemNameTextView: TextView = view.findViewById(R.id.itemName)
            val itemPriceTextView: TextView = view.findViewById(R.id.itemPrice)
            val itemImageView: ImageView = view.findViewById(R.id.itemImg)

            itemNameTextView.text = item.name
            itemPriceTextView.text = "$${item.price}"

            // Use Glide to load the image from URL
            Glide.with(context)
                .load("http://10.0.2.2:8000" + item.imageUrl)  // Full URL from the backend
                .placeholder(R.drawable.add1)  // A default placeholder image
                .error(R.drawable.add2)  // Fallback image in case of error
                .into(itemImageView)

            return view
        }
    }
}
