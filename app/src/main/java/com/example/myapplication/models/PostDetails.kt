package com.example.myapplication.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostDetails {
    @SerializedName("id")
    @Expose
    var id: Int? = 0
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
}