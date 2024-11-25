package com.example.glambooker.ui.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.glambooker.data.entity.Filter
import com.example.glambooker.data.entity.Workplace
import com.example.glambooker.data.repo.WorkplaceRepository
import com.example.glambooker.myApp.MyApplication

class BottomBookingViewModel (application: Application) :AndroidViewModel(application) {
    val repository: WorkplaceRepository by lazy {
        WorkplaceRepository(application as MyApplication)
    }

    var wrep = WorkplaceRepository(MyApplication())
    var workplaceList = MutableLiveData<List<Workplace>>()
    var workplaceList1 = MutableLiveData<List<Workplace>>()
    var filter = MutableLiveData<Filter>()

    fun uploadWorkplaces(){
        workplaceList.value = repository.uploadWorkplaces()
    }

    fun uploadFilterWorkplaces(filter: Filter) :MutableLiveData<List<Workplace>> {
        workplaceList1.value = repository.uploadFilterWorkplacesFilter(filter)
        return workplaceList1
    }

    init {
        uploadWorkplaces()
    }


}