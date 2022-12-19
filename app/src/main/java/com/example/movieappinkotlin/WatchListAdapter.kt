package com.example.movieappinkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class WatchListAdapter(

    private var items: List<WatchList>,
    private val onItemClick: (item: WatchList) -> Unit
) : RecyclerView.Adapter<WatchListAdapter.WatchListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_watch_list, parent, false)
        return WatchListHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WatchListHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(items: List<WatchList>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class WatchListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_watch_list_poster)
        fun bind(item: WatchList) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${item.posterPath}")
                .transform(CenterCrop())
                .into(poster)
            itemView.setOnClickListener { onItemClick.invoke(item) }
        }
    }
}