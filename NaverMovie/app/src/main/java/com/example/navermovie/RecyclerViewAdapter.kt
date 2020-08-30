package com.example.navermovie

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class RecyclerViewAdapter :
    RecyclerView.Adapter<ViewHolder>(){

    private val items = mutableListOf<MovieItem>()

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_layout, parent, false)
        return ViewHolder(view)
    }

    fun replaceAll(items: List<MovieItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}


class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(data: MovieItem){
        Glide.with(itemView.context).load(data.image)
            .apply(RequestOptions().override(300, 450))
            .apply(RequestOptions.centerCropTransform())
            .into(itemView.imageView)

        val title = Html.fromHtml(data.title, Html.FROM_HTML_MODE_LEGACY)
        itemView.textView_title.text = title.toString()
        itemView.textView_actor.text = "출연 ${data.actor}"
        itemView.textView_director.text = "감독 ${data.director}"

        //클릭시 웹사이트 연결
        itemView.setOnClickListener{
            val webpage = Uri.parse("${data.link}")
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            itemView.context.startActivity(webIntent);
        }
    }
}