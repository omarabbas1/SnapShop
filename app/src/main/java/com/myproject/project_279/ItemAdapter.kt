package com.myproject.project_279

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ItemAdapter(private val items: List<Item>, private val onItemClick: (Item) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onItemClick)
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val productImage: ImageView = view.findViewById(R.id.product_image)
        private val productName: TextView = view.findViewById(R.id.product_name)
        private val productPrice: TextView = view.findViewById(R.id.product_price)

        fun bind(item: Item, onItemClick: (Item) -> Unit) {
            productName.text = item.name
            productPrice.text = "$${item.price}"
            // Set image using a library like Glide or Picasso
            Glide.with(itemView.context).load(item.image_url).into(productImage)

            itemView.setOnClickListener { onItemClick(item) }
        }
    }
}
