package com.myproject.project_279

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SearchAdapter(private var items: List<Item>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    // Create ViewHolder for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return SearchViewHolder(view)
    }

    // Bind data to each item view
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = items[position]
        holder.productName.text = item.name
        holder.productPrice.text = item.price.toString() // Convert Double to String
        // Use Glide to load images (or you can use another image loading library)
        Glide.with(holder.productImage.context).load(item.image_url).into(holder.productImage)

        // Add click listener for "Add to Cart" button
        holder.addToCartButton.setOnClickListener {
            // Handle Add to Cart functionality (e.g., adding item to a shopping cart)
            // You can show a Toast or update the cart view as needed
        }
    }

    // Return the size of the data
    override fun getItemCount(): Int = items.size

    // Update the list of items when new data is fetched
    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }

    // Define the ViewHolder for each item
    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)
    }
}
