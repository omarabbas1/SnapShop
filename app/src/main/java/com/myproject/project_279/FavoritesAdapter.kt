package com.myproject.project_279

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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
//        val favoriteCheckBox: CheckBox = itemView.findViewById(R.id.heartIcon)

        fun bind(item: Item) {
            itemName.text = item.name
            itemPrice.text = "$${item.price}"  // Display price

            // Ensure the full image URL is used
            val imageUrl = "http://10.0.2.2:8000" + item.imageUrl // Assuming image URL needs this base URL
            Glide.with(context)
                .load(imageUrl)  // Glide will load the image from the complete URL
                .into(itemImage)

            // Set CheckBox state based on item.isFavorite
//            favoriteCheckBox.isChecked = item.isFavorite
//
//            // Handle the checkbox state change
//            favoriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
//                item.isFavorite = isChecked
//                onFavoriteClick(item)  // Notify that the item has been added/removed from favorites
//            }
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
