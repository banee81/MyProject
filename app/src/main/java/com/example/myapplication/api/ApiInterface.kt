package com.example.myapplication.api

import com.example.myapplication.models.Post
import com.example.myapplication.models.PostDetails
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Url
import retrofit2.http.GET

interface ApiInterface {
    @GET
    fun getPosts(@Url url: String?): Call<MutableList<Post>>?

    @GET("{id}")
    fun getPostDetails(
        @Path("id") id: Int,): Call<PostDetails>?

}