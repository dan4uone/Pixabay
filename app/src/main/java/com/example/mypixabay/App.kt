package com.example.mypixabay

import android.app.Application
import com.example.mypixabay.model.PixaApi
import com.example.mypixabay.model.RetrofitService


class App : Application() {

    companion object {
       lateinit var api: PixaApi

    }
    override fun onCreate() {
            super.onCreate()
            api = RetrofitService().getApi()
        }

}




