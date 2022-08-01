package com.example.githubusersearch

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Header
import retrofit2.http.Query
import java.lang.reflect.Type

interface GitHubAPIService {
// https://api.github.com/users/seoyeon-double-7

    @GET("/users/{userId}")
    fun getUser(
        @Path("userId") id: String,  //id에 깃허브 사용자 이름이 들어감
        @Header("Authorization") pat: String
    ): Call<GitHubUser>
}
data class GitHubUser(val id : Int, val login : String, val name : String?, val following: Int, val followers: Int, val avatar_url : String)

//class GitHubDeserializer : JsonDeserializer<GitHubUser>{
//        override fun deserialize(
//            json: JsonElement?,
//            typeOfT: Type?,
//            context: JsonDeserializationContext?
//        ): GitHubUser {
//            val root = json?.asJsonObject
//            val login = root?.getAsJsonPrimitive("login")?.asString
//            val id = root?.getAsJsonPrimitive("id")?.asInt
//
//            return GitHubUser(id!!, login!!)
//        }
//}