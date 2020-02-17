package com.concept.shop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.concept.shop.R
import com.concept.shop.adapter.ThumbsAdapter.ThumbsViewHolder

class ThumbsAdapter : RecyclerView.Adapter<ThumbsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbsViewHolder {
        return ThumbsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_thumbs, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ThumbsViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return 4
    }

    class ThumbsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}