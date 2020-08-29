package com.example.navermovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = ArrayList<MovieItem>()
        list.add(MovieItem(getDrawable(R.drawable.image01)!!,getString(R.string.title01)))
        list.add(MovieItem(getDrawable(R.drawable.image02)!!,getString(R.string.title02)))
        list.add(MovieItem(getDrawable(R.drawable.image03)!!,getString(R.string.title03)))
        list.add(MovieItem(getDrawable(R.drawable.image04)!!,getString(R.string.title04)))
        list.add(MovieItem(getDrawable(R.drawable.image05)!!,getString(R.string.title05)))

        val adapter = RecyclerViewAdapter(list)
        recyclerView.adapter = adapter


    }
}