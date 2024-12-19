package com.example.glambooker.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.glambooker.data.entity.Adress
import com.example.glambooker.data.entity.Workplace
import com.example.glambooker.data.repo.WorkplaceRepository
import com.example.glambooker.myApp.MyApplication
import kotlinx.coroutines.launch

class BeBossViewModel(application: Application):AndroidViewModel(application) {
    private val repository: WorkplaceRepository by lazy {
        WorkplaceRepository(application as MyApplication)
    }

    var adressList = MutableLiveData<List<Adress>>()

    private fun uploadAdresses (){
        viewModelScope.launch {
            val list = repository.uploadCities()
            if (list .isEmpty()){
                Log.e("emty","emty")
            }
            else {
                adressList.postValue(list)
            }
        }

    }

     fun saveWorkplace(workplace: Workplace){
        repository.saveWorkplace(workplace)
    }

    init {
        uploadAdresses()
    }
}