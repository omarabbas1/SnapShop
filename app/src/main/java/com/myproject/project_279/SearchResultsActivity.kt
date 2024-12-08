package com.myproject.project_279

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity





import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

import com.bumptech.glide.Glide
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class SearchResultsActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        listView = findViewById(R.id.ItemsListView1)


        val searchQuery = intent.getStringExtra("SEARCH_QUERY") ?: ""

        if (searchQuery.isNotEmpty()) {
            searchItems(searchQuery)
        } else {
            Toast.makeText(this, "Search query is empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchItems(query: String) {
        val request = Request.Builder()
            .url("http://10.0.2.2:8000/search/$query")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@SearchResultsActivity, "Failed to load items", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.let { responseBody ->
                    val jsonResponse = JSONObject(responseBody.string())
                    val itemsList = mutableListOf<Item>()

                    if (jsonResponse.has("items")) {
                        val items = jsonResponse.getJSONArray("items")
                        for (i in 0 until items.length()) {
                            val item = items.getJSONObject(i)
                            val itemName = item.getString("name")
                            val itemPrice = item.getString("price")
                            val itemImageUrl = item.getString("image_url")

                            itemsList.add(Item(itemName, itemPrice, itemImageUrl))
                        }
                    }

                    runOnUiThread {
                        if (itemsList.isEmpty()) {
                            findViewById<TextView>(R.id.noResultsTextView).visibility = android.view.View.VISIBLE
                            listView.visibility = android.view.View.GONE
                        } else {
                            findViewById<TextView>(R.id.noResultsTextView).visibility = android.view.View.GONE
                            listView.visibility = android.view.View.VISIBLE
                            val adapter = SearchItemAdapter(this@SearchResultsActivity, itemsList)
                            listView.adapter = adapter
                        }
                    }
                } ?: run {
                    runOnUiThread {
                        findViewById<TextView>(R.id.noResultsTextView).visibility = android.view.View.VISIBLE
                        listView.visibility = android.view.View.GONE
                    }
                }
            }

        })
    }

    inner class SearchItemAdapter(
        private val context: SearchResultsActivity,
        private val items: List<Item>
    ) : BaseAdapter() {

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

            val itemNameTextView: TextView = view.findViewById(R.id.itemName)
            val itemPriceTextView: TextView = view.findViewById(R.id.itemPrice)
            val itemImageView: ImageView = view.findViewById(R.id.itemImg)
            val heartIcon: CheckBox = view.findViewById(R.id.heartIcon)
            val addToCartButton: Button = view.findViewById(R.id.add_to_cart_button)

            itemNameTextView.text = item.name
            itemPriceTextView.text = "$${item.price}"

            Glide.with(context)
                .load("http://10.0.2.2:8000" + item.imageUrl)
                .placeholder(R.drawable.add1)
                .into(itemImageView)


            heartIcon.isChecked = FavoritesHelper.isFavorite(context, item)


            heartIcon.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    FavoritesHelper.addFavorite(context, item)
                    Toast.makeText(context, "${item.name} added to favorites", Toast.LENGTH_SHORT).show()
                } else {
                    FavoritesHelper.removeFavorite(context, item)
                    Toast.makeText(context, "${item.name} removed from favorites", Toast.LENGTH_SHORT).show()
                }
            }


            addToCartButton.setOnClickListener {
                AddToCartHelper.addItemToCart(context, item) // Call the CartHelper to add to cart
                Toast.makeText(context, "${item.name} added to cart", Toast.LENGTH_SHORT).show()
            }

            return view
        }
    }
}

