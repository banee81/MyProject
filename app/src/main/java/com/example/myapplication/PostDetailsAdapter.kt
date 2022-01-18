package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.models.PostDetails

class PostDetailsAdapter(context: Context,
                         private val dataSource: MutableList<PostDetails>):
    ArrayAdapter<PostDetails>(context, 0, dataSource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.post_details, parent, false)
        val postDetails = getItem(position) as PostDetails
        view.findViewById<TextView>(R.id.postDetailsName).text = postDetails?.name
        view.findViewById<TextView>(R.id.postDetailsEmail).text = postDetails?.email

        return view
    }
}