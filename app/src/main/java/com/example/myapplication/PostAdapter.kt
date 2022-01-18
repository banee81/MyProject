package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.models.Post

class PostAdapter(context: Context,
                  private val dataSource: MutableList<Post>):
    ArrayAdapter<Post>(context, 0, dataSource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.post_item, parent, false)
        val post = getItem(position) as Post
        view.findViewById<TextView>(R.id.postTitle).text = post?.title
        view.findViewById<TextView>(R.id.postText).text = post?.body

        return view
    }
}