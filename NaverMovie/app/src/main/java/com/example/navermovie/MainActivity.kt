package com.example.navermovie

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.net.URLEncoder




class MainActivity : AppCompatActivity() {

    companion object {
        private const val clientId = "kHLvopePGjYBNBI1P4Us"
        private const val clientSecret = "GWjKNlXW_n"
    }
    
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        
        
        searchBtn.setOnClickListener {
            //키워드 없으면
            if (searchText.text.isEmpty()){
                return@setOnClickListener
            }

            //API
            submitSearch(searchText.text.toString())

            //키보드를 내린다.
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(searchText.windowToken, 0)
        }

    }
    private fun submitSearch(keyWord: String) {
        //OkHttp로 요청하기
        val text = URLEncoder.encode(keyWord, "UTF-8")
        println(text)
        val url =
            URL("https://openapi.naver.com/v1/search/movie.json?query=${text}&display=10&start=1&genre=1")

        val request = Request.Builder()
            .url(url)
            .addHeader("X-Naver-Client-Id", clientId)
            .addHeader("X-Naver-Client-Secret", clientSecret)
            .method("GET", null)
            .build()

        val client = OkHttpClient.Builder()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: okhttp3.Call, response: Response?) {
                val body = response?.body()?.string()
                println("Success to execute request : $body")

                //Gson을 Kotlin에서 사용 가능한 object로 만든다.
                val gson = GsonBuilder().create()
                //아! 이렇게 하는구나
                val homefeed = gson.fromJson(body, Homefeed::class.java)

                //어답터를 연결하자. 메인쓰레드 변경하기 위해 이 메소드 사용
                runOnUiThread {
                    recyclerViewAdapter.replaceAll(homefeed.items)

                    searchText.setText("")
                }
            }
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("Failed to execute request ${e.message}")
            }
        })
    }

}
