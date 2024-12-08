package com.myproject.project_279


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SearchAdapter(
    private var items: List<Item>,
    private val onAddToCartClicked: (Item) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_fashion, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = items[position]


        holder.itemNameTextView.text = item.name
        holder.itemPriceTextView.text = "$${item.price}"


        val imageUrl = "http://10.0.2.2:8000${item.imageUrl}"



        Glide.with(holder.itemImageView.context)
            .load(imageUrl)

            .into(holder.itemImageView)


        holder.addToCartButton.setOnClickListener {
            onAddToCartClicked(item)
        }
    }

    override fun getItemCount(): Int = items.size


    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }


    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemName)
        val itemPriceTextView: TextView = itemView.findViewById(R.id.itemPrice)
        val itemImageView: ImageView = itemView.findViewById(R.id.itemImg)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)
    }
}
