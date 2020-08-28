package com.example.navermovie

import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (private val items: ArrayList<MovieItem>) :
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerViewAdapter.ViewHolder {
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(listener: View.OnClickListener, item: MovieItem){

        }
    }
}