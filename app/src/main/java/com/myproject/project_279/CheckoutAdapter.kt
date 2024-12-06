package com.myproject.project_279

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.myproject.project_279.Item

class CheckoutAdapter(
    private val cartItems: ArrayList<Item>,
    private val context: Context,
    private val onQuantityChanged: () -> Unit // Callback to update the total price
) : RecyclerView.Adapter<CheckoutAdapter.CartViewHolder>() {

    // Calculate total price when the adapter is initialized
    private fun calculateTotalPrice(): Double {
        var total = 0.0
        for (item in cartItems) {
            total += item.price.toDouble() * item.quantity // Ensure both are numbers
        }
        return total
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_checkout, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.productName.text = cartItem.name
        holder.productPrice.text = "$${cartItem.price.format(2)}" // Format price with 2 decimal places

        // Set the initial quantity
        holder.quantityText.text = cartItem.quantity.toString()


        val imageUrl = "http://10.0.2.2:8000" + cartItem.imageUrl
        Glide.with(context)
            .load(imageUrl)
            .into(holder.productImage)


        // Increase quantity button click listener
        holder.increaseButton.setOnClickListener {
            cartItem.quantity++
            holder.quantityText.text = cartItem.quantity.toString()
            onQuantityChanged() // Update total price when quantity changes
            notifyItemChanged(position) // Notify RecyclerView that an item has changed
        }

        // Decrease quantity button click listener
        holder.decreaseButton.setOnClickListener {
            if (cartItem.quantity > 1) { // Prevent quantity from going below 1
                cartItem.quantity--
                holder.quantityText.text = cartItem.quantity.toString()
                onQuantityChanged() // Update total price when quantity changes
                notifyItemChanged(position) // Notify RecyclerView that an item has changed
            }
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    // ViewHolder class
    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val quantityText: TextView = itemView.findViewById(R.id.product_quantity)
        val increaseButton: ImageButton = itemView.findViewById(R.id.increase_quantity)
        val decreaseButton: ImageButton = itemView.findViewById(R.id.decrease_quantity)
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
    }
}


