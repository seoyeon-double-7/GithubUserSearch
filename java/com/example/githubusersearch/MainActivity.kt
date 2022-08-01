package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(
                GsonConverterFactory.create(
//                    GsonBuilder().registerTypeAdapter(
//                        GitHubUser::class.java,
//                        GitHubDeserializer()
//                    ).create()
                )
            )
            .build()

        val userIdInput = findViewById<EditText>(R.id.edit_message)
        val find_btn = findViewById<Button>(R.id.search_btn)
        val apiService = retrofit.create(GitHubAPIService::class.java)





        find_btn.setOnClickListener {
            val id = userIdInput.text.toString()
            val apiCallForData = apiService.getUser(id, "token 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")
            apiCallForData.enqueue(object : Callback<GitHubUser>{
                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
                    val data = response.body()!!
                    Log.d("mytag", data.toString())

                    val context = findViewById<TextView>(R.id.context)
                    val profile_image = findViewById<ImageView>(R.id.image_profile)

                    context.text = "login : ${data.login} \n id : ${data.id} \n name : ${data.name} \n" +
                            "following : ${data.following.toString()} \n followers : ${data.followers.toString()}"
                    Glide.with(this@MainActivity).load(data.avatar_url).into(profile_image);
                }

                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
                    Toast.makeText(this@MainActivity,
                        "에러 발생 : ${t.message}",
                        Toast.LENGTH_SHORT).show()
                }

            })

        }
    }


}