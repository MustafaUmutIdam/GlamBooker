package com.example.glambooker.ui.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.glambooker.data.entity.Adress
import com.example.glambooker.data.repo.WorkplaceRepository
import com.example.glambooker.myApp.MyApplication
import kotlinx.coroutines.launch

class FilterPageViewModel (application: Application): AndroidViewModel(application) {
    val repository: WorkplaceRepository by lazy {
        WorkplaceRepository(application as MyApplication)
    }

    var wrep = WorkplaceRepository(MyApplication())
    var citiesList = MutableLiveData<List<Adress>>()

    fun uploadCities (){
        viewModelScope.launch {
            val list = repository.uploadCities()
            if (list .isNullOrEmpty()){
                Log.e("emty","emty")
            }
            else {
                citiesList.postValue(list)
            }
        }

    }
    init {
        uploadCities()
    }

}