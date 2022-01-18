package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.myapplication.viewmodels.PostsViewModel
import org.koin.android.ext.android.inject
import android.widget.AdapterView.OnItemClickListener

class FirstFragment : Fragment() {

    private val postsViewModel: PostsViewModel by inject()
    private lateinit var adapter: PostAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = PostAdapter(context, mutableListOf())
        getPosts()
        initObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_first, container, false)

        val lwPosts: ListView = view.findViewById(R.id.lwPosts)
        lwPosts.adapter = adapter

        lwPosts.setOnItemClickListener(OnItemClickListener { arg0, arg1, position, arg3 ->

            val bundle = Bundle()
            bundle.putInt("position", position)

            val fragment = SecondFragment()
            fragment.setArguments(bundle)
            //val transaction = fragmentManager?.beginTransaction()
            val transaction = parentFragmentManager?.beginTransaction()
            transaction?.replace(R.id.navContainer, fragment)?.commit()
        })

        return view
    }

    private fun getPosts(){
        postsViewModel.getPosts()
    }

    private fun initObservers() {
        postsViewModel.posts.observe({ lifecycle }) {
            it?.let {
                adapter.clear()
                adapter.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

}