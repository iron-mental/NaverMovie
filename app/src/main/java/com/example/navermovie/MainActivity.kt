package com.example.navermovie

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private val clientId = "kHLvopePGjYBNBI1P4Us"
    private val clientSecret = "GWjKNlXW_n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBtn.setOnClickListener {
            //키워드 없으면
            if (searchText.text.isEmpty()) {
                return@setOnClickListener
            }

            //레이아웃매니저 설정
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            recyclerView.setHasFixedSize(true)
            //API
            searchByKeyword(searchText.text.toString())

            //키보드를 내린다.
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(searchText.windowToken, 0)
        }
    }

    private fun searchByKeyword(keyword: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server = retrofit.create(NaverAPI::class.java)
        val callGetSearchMovie = server.getSearchMovie(clientId, clientSecret, keyword)

        callGetSearchMovie.enqueue(object : Callback<MovieSearchRequest> {
            override fun onResponse(call: Call<MovieSearchRequest>, response: Response<MovieSearchRequest>) {
                Log.d("TAG", "성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<MovieSearchRequest>, t: Throwable) {
                Log.d("TAG", "실패 : $t")
            }
        })
    }
}
