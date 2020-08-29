package com.example.navermovie

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class RecyclerViewAdapter (private val items: ArrayList<MovieItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerViewAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_layout, parent, false)
        return RecyclerViewAdapter.ViewHolder(view)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var test: View = v
        fun bind(item: MovieItem){
            test.imageView.setImageDrawable(item.image)
            test.textView_title.text = item.title
        }
    }
}