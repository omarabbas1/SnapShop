package com.myproject.project_279

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView

import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CheckoutAdapter(
    private val cartItems: ArrayList<Item>,
    private val context: Context,
    private val onQuantityChanged: () -> Unit
) : RecyclerView.Adapter<CheckoutAdapter.CartViewHolder>() {


    private fun calculateTotalPrice(): Double {
        var total = 0.0
        for (item in cartItems) {
            total += item.price.toDouble() * item.quantity
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
        holder.productPrice.text = "$${cartItem.price.format(2)}"


        holder.quantityText.text = cartItem.quantity.toString()


        val imageUrl = "http://10.0.2.2:8000" + cartItem.imageUrl
        Glide.with(context)
            .load(imageUrl)
            .into(holder.productImage)



        holder.increaseButton.setOnClickListener {
            cartItem.quantity++
            holder.quantityText.text = cartItem.quantity.toString()
            onQuantityChanged()
            notifyItemChanged(position)
        }


        holder.decreaseButton.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                holder.quantityText.text = cartItem.quantity.toString()
                onQuantityChanged()
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }


    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val quantityText: TextView = itemView.findViewById(R.id.product_quantity)
        val increaseButton: ImageButton = itemView.findViewById(R.id.increase_quantity)
        val decreaseButton: ImageButton = itemView.findViewById(R.id.decrease_quantity)
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
    }
}


