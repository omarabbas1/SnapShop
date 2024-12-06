package com.myproject.project_279

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AddToCartAdapter(
    private val cartItems: MutableList<Item>,
    private val context: Context,
    private val onRemoveFromCartClick: (Item) -> Unit
) : RecyclerView.Adapter<AddToCartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        val removeButton: Button = itemView.findViewById(R.id.remove_from_cart_button)

        fun bind(item: Item) {
            itemName.text = item.name
            itemPrice.text = "$${item.price}"

            val imageUrl = "http://10.0.2.2:8000" + item.imageUrl
            Glide.with(context)
                .load(imageUrl)
                .into(itemImage)

            // Handle remove button click
            removeButton.setOnClickListener {
                onRemoveFromCartClick(item)  // Call the callback to remove the item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    override fun getItemCount(): Int = cartItems.size
}

