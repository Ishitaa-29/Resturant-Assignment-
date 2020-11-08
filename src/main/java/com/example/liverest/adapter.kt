package com.example.liverest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(var restList: MutableList<rest>): RecyclerView.Adapter<adapter.viewHolder>(){

    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameText: TextView = itemView.findViewById(R.id.item_name)
        val addressText: TextView = itemView.findViewById(R.id.item_address)
        val ratingText: TextView = itemView.findViewById(R.id.item_rating)
        val specialText: TextView = itemView.findViewById(R.id.item_special)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.nameText.text = restList[position].name
        holder.addressText.text = restList[position].address
        holder.ratingText.text = restList[position].rating.toString()
        holder.specialText.text = restList[position].special
    }

    override fun getItemCount(): Int {
        return restList.count()
    }

}