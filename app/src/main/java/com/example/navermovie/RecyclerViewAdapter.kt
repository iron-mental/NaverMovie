package com.example.navermovie

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class RecyclerViewAdapter(pitrivate val homeFeed: HomeFeed) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return homeFeed.items.count()
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(homeFeed.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerViewAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_layout, parent, false)
        return RecyclerViewAdapter.ViewHolder(view)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Item){
            Glide.with(view.context).load(data.image)
                .apply(RequestOptions().override(300, 450))
                .apply(RequestOptions.centerCropTransform())
                .into(view.imageView)
            itemView.textView_title.text = data.title
            itemView.textView_actor.text = "출연 ${data.actor}"
            itemView.textView_director.text = "감독 ${data.director}"

            //클릭시 웹사이트 연결
            itemView.setOnClickListener{
                val webpage = Uri.parse("${data.link}")
                val webIntent = Intent(Intent.ACTION_VIEW, webpage)
                view.getContext().startActivity(webIntent);
            }
        }
    }
}