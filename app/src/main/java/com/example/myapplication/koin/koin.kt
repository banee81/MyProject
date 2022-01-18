package com.example.myapplication.koin

import com.example.myapplication.viewmodels.PostsViewModel
import org.koin.dsl.module


val koinModule = module {

    single {
        PostsViewModel(get())
    }

}
