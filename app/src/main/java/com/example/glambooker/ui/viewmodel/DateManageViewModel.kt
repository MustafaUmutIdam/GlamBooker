package com.example.glambooker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.glambooker.data.entity.Date
import com.example.glambooker.data.repo.WorkplaceRepository
import com.example.glambooker.myApp.MyApplication

class DateManageViewModel(application: Application): AndroidViewModel(application) {
    private val repository: WorkplaceRepository by lazy {
        WorkplaceRepository(application as MyApplication)
    }
    var dateList = MutableLiveData<List<Date>>()

    fun uploadDateList(){
        dateList = repository.uploadDates()
    }

    init {
        uploadDateList()
    }
}