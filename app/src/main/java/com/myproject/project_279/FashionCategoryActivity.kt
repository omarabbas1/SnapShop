package com.myproject.project_279

import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class FashionCategoryActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fashion_category)

        listView = findViewById(R.id.fashionItemsListView)

        val category = intent.getStringExtra("category") ?: "fashion"
        fetchItemsByCategory(category)
    }

    private fun fetchItemsByCategory(category: String) {
        val request = Request.Builder()
            .url("http://10.0.2.2:8000/api/items/$category")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@FashionCategoryActivity, "Failed to load items", Toast.LENGTH_SHORT).show()
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

                        runOnUiThread {
                            val adapter = FashionItemAdapter(this@FashionCategoryActivity, itemsList)
                            listView.adapter = adapter
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@FashionCategoryActivity, "No items found", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

//    data class Item(val name: String, val price: String, val imageUrl: String)

    inner class FashionItemAdapter(private val context: FashionCategoryActivity, private val items: List<Item>) :
        BaseAdapter() {

        override fun getCount(): Int = items.size

        override fun getItem(position: Int): Any = items[position]

        override fun getItemId(position: Int): Long = position.toLong()

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

            // Handle Heart Icon (Favorites)
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

            // Handle Add to Cart Button
            addToCartButton.setOnClickListener {
                AddToCartHelper.addItemToCart(context, item) // Call the CartHelper to add to cart
                Toast.makeText(context, "${item.name} added to cart", Toast.LENGTH_SHORT).show()
            }

            return view
        }
    }
}