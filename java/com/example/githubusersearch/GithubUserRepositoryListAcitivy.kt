package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubUserRepositoryListAcitivy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_user_repository_list_acitivy)

        val UserId = intent.getStringExtra("id").toString()
        Log.d("mytag", UserId)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
        val apiService = retrofit.create(GitHubAPIService::class.java)

        val apiCallForData = apiService.getRepos(UserId, "token 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")
        apiCallForData.enqueue(object : Callback<List<GitRepos>> {

            override fun onResponse(call: Call<List<GitRepos>>, response: Response<List<GitRepos>>) {

                if(response.code().toString().startsWith("4")){
                    Toast.makeText(this@GithubUserRepositoryListAcitivy,
                        "올바른 github 아이디를 다시 입력해주세요",
                        Toast.LENGTH_SHORT).show()
                }else{
                    val data = response.body()!!
                    Log.d("mytag", data.toString())
                    val listView = findViewById<RecyclerView>(R.id.gihub_user_list)
                    listView.setHasFixedSize(true)
                    listView.layoutManager = LinearLayoutManager(this@GithubUserRepositoryListAcitivy)

                    listView.adapter = GithubReposAdapter(data)
                    listView.setHasFixedSize(true)
                    }


            }

            override fun onFailure(call: Call<List<GitRepos>>, t: Throwable) {
                Toast.makeText(this@GithubUserRepositoryListAcitivy,
                    "에러 발생 : ${t.message}",
                    Toast.LENGTH_SHORT).show()
            }

        })

    }
}