package com.isarthaksharma.melody

import apiInterface
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView:RecyclerView
    lateinit var myDataAdaptor: myDataAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            //coding ------->
            myRecyclerView = findViewById(R.id.myRecyclerView)


            // Define your base URL here
            val baseUrl = "https://deezerdevs-deezer.p.rapidapi.com/"

            // Retrofit instance (you can set up other configurations here)
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(apiInterface::class.java)

            val retrofitData = retrofit.getData("eminem")

            retrofitData.enqueue(object : Callback<musicDataClass?> {
                override fun onResponse(
                    p0: Call<musicDataClass?>,
                    p1: Response<musicDataClass?>
                ) {
                    //if the API call is success then this method is executed
                    Toast.makeText(this@MainActivity, "Responded", Toast.LENGTH_SHORT).show()

                    val dataList = p1.body()?.data!!
                    myDataAdaptor = myDataAdaptor(this@MainActivity,dataList)
                    myRecyclerView.adapter = myDataAdaptor
                    myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    Log.d("Tag:onResponse","onResponse: "+p1.body())

                }

                override fun onFailure(p0: Call<musicDataClass?>, p1: Throwable) {
                    //if the API call is a failure then this method is executed
                    Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
                    Log.d("Tag:onFailure","onFailure: "+p1.message)

                }
            })




            insets
        }
    }
}