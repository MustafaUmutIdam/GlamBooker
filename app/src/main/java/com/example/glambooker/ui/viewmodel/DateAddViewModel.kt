package com.example.glambooker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.glambooker.data.entity.Date
import com.example.glambooker.data.repo.WorkplaceRepository
import com.example.glambooker.myApp.MyApplication

class DateAddViewModel(application: Application): AndroidViewModel(application) {
    private val repository: WorkplaceRepository by lazy {
        WorkplaceRepository(application as MyApplication)
    }

    fun saveDates(date:Date){
        repository.saveDates(date)
    }
}