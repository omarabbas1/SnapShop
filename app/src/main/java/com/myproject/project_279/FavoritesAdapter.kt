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

class FavoritesAdapter(
    private val itemList: MutableList<Item>,
    private val context: Context,
    private val onFavoriteClick: (Item) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        val removeButton: Button = itemView.findViewById(R.id.remove_from_favorites_button)

        fun bind(item: Item) {
            itemName.text = item.name
            itemPrice.text = "$${item.price}"


            val imageUrl = "http://10.0.2.2:8000" + item.imageUrl
            Glide.with(context)
                .load(imageUrl)
                .into(itemImage)

            removeButton.setOnClickListener {
                onFavoriteClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemList.size
}