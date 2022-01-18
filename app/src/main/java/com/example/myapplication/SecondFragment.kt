package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.viewmodels.PostsViewModel
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject

class SecondFragment : Fragment() {

    private  var position = 0
    private val postsViewModel: PostsViewModel by inject()
    private lateinit var adapter: PostDetailsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = PostDetailsAdapter(context, mutableListOf())
        val bundle = this.arguments

        if (bundle != null) {
            position= bundle.getInt("position")
        }
        getPostDetails()
        initObservers()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val lwPostDetails: ListView = view.findViewById(R.id.lwPostsDetails)
        lwPostDetails.adapter = adapter

        lwPostDetails.setOnItemClickListener( { arg0, arg1, position, arg3 ->

            deletePostClick()
        })

        return view
    }

    private fun getPostDetails(){
        postsViewModel.getPostDetails(position)
    }

    private fun initObservers() {
        postsViewModel.post.observe({ lifecycle }) {
            it?.let {
                adapter.clear()
                adapter.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun deletePostClick(){
        activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Delete Post")
            builder.setMessage("Are u sure u want to delete post?")
            builder.setPositiveButton("Yes"){dialog, which ->
                // Do something when user press the positive button
                val fragment = FirstFragment()
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.navContainer, fragment)?.commit()
            }
            builder.setNegativeButton("No"){dialog,which ->

            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }

}