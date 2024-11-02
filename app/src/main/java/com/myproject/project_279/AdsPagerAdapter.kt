package com.myproject.project_279

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AdsPagerAdapter(private val adList: List<Int>) : RecyclerView.Adapter<AdsPagerAdapter.AdsViewHolder>() {

    class AdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val adImageView: ImageView = itemView.findViewById(R.id.adImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ad_item, parent, false)
        return AdsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        holder.adImageView.setImageResource(adList[position]) // Set the image resource from the ad list
    }

    override fun getItemCount(): Int {
        return adList.size
    }
}
