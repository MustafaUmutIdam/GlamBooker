package com.example.glambooker.myApp

import android.app.Application
import com.example.glambooker.data.datasource.AdressDataSource

class MyApplication : Application() {
    private lateinit var adressDataSource: AdressDataSource

    override fun onCreate() {
        super.onCreate()
        adressDataSource = AdressDataSource(applicationContext)
    }
}