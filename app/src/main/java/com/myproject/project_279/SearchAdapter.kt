package com.myproject.project_279

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class SearchAdapter(
    private var items: List<Item>,
    private val onAddToCartClicked: (Item) -> Unit // Callback for handling the "Add to Cart" button
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_fashion, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = items[position]

        // Set item name and price
        holder.itemNameTextView.text = item.name
        holder.itemPriceTextView.text = "$${item.price}" // Format price as currency

        // Check the URL being loaded and log it for debugging
        val imageUrl = "http://10.0.2.2:8000${item.imageUrl}"
        Log.d("ImageURL", "Loading Image from URL: $imageUrl")

        // Use Glide to load images with a placeholder and error image
        Glide.with(holder.itemImageView.context)
            .load(imageUrl) // Concatenate base URL with item image URL
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.add1) // Placeholder image while loading
                    .error(R.drawable.add2) // Error image if loading fails
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache images for faster future loads
            )
            .into(holder.itemImageView)

        // Add click listener for the "Add to Cart" button
        holder.addToCartButton.setOnClickListener {
            onAddToCartClicked(item)
        }
    }

    override fun getItemCount(): Int = items.size

    // Update the list of items when new data is fetched
    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged() // Notify adapter that the data has changed
    }

    // ViewHolder class for each item in the RecyclerView
    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemName)
        val itemPriceTextView: TextView = itemView.findViewById(R.id.itemPrice)
        val itemImageView: ImageView = itemView.findViewById(R.id.itemImg)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)
    }
}
