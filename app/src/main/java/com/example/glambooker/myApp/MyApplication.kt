package com.example.glambooker.myApp

import android.app.Application
import com.example.glambooker.data.datasource.AdressDataSource

class MyApplication : Application() {
    lateinit var adressDataSource: AdressDataSource
    private set

    override fun onCreate() {
        super.onCreate()
        adressDataSource = AdressDataSource(applicationContext)
    }
}