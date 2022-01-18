package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.RetrofitBuilder
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.models.Post
import com.example.myapplication.models.PostDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PostsViewModel(application: Application): ViewModel() {

    var posts = MutableLiveData<List<Post>>(mutableListOf())
    var post = MutableLiveData<PostDetails>()

    fun getPosts() {

        var retrofitBuilder = RetrofitBuilder()
        val baseUrl = "https://jsonplaceholder.typicode.com/posts/"
        val retrofit: Retrofit = retrofitBuilder.getRetrofitBulder(baseUrl)
        val api = retrofit.create(ApiInterface::class.java)
        val call: Call<MutableList<Post>>? = api.getPosts(baseUrl)
        call!!.enqueue(object : Callback<MutableList<Post>> {

            override fun onResponse(call: Call<MutableList<Post>>,
                response: Response<MutableList<Post>>) {

                val usersData = mutableListOf<Post>()
                val lista = response.body() as MutableList<Post>
                for (i in 0 until lista.size){
                    usersData.add(lista[i])
                }
                posts.postValue(usersData)
            }

            override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {

            }
        })
    }

    fun getPostDetails(position: Int) {
        var retrofitBuilder = RetrofitBuilder()
        val baseUrl = "https://jsonplaceholder.typicode.com/users/"
        val retrofit: Retrofit = retrofitBuilder.getRetrofitBulder(baseUrl)
        val api = retrofit.create(ApiInterface::class.java)
        val call: Call<PostDetails>? = api.getPostDetails(position)
        call!!.enqueue(object : Callback<PostDetails> {

            override fun onResponse(call: Call<PostDetails>, response: Response<PostDetails>) {
                post.postValue(response.body())
            }

            override fun onFailure(call: Call<PostDetails>, t: Throwable) {

            }
        })
    }
}


