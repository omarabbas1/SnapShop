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
import java.io.IOException

class SportsCategoryActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_category)

        listView = findViewById(R.id.sportsItemsListView)

        // Fetch sports items from the backend API
        fetchItemsByCategory("sport")
    }

    private fun fetchItemsByCategory(category: String) {
        val request = Request.Builder()
            .url("http://10.0.2.2:8000/api/items/$category") // Endpoint for sports items
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@SportsCategoryActivity, "Failed to load items", Toast.LENGTH_SHORT).show()
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

                            // Add the item to the list
                            itemsList.add(Item(itemName, itemPrice, itemImageUrl))
                        }

                        // Update the ListView on the main thread
                        runOnUiThread {
                            val adapter = SportsItemAdapter(this@SportsCategoryActivity, itemsList)
                            listView.adapter = adapter
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@SportsCategoryActivity, "No items found for category", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

    data class Item(val name: String, val price: String, val imageUrl: String)

    inner class SportsItemAdapter(private val context: SportsCategoryActivity, private val items: List<Item>) :
        BaseAdapter() {

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

            itemNameTextView.text = item.name
            itemPriceTextView.text = "$${item.price}"

            Glide.with(context)
                .load("http://10.0.2.2:8000" + item.imageUrl)
                .placeholder(R.drawable.add1)
                .into(itemImageView)

            return view
        }
    }
}
