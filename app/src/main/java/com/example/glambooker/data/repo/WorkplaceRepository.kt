package com.example.glambooker.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.glambooker.data.datasource.AdressDataSource
import com.example.glambooker.data.datasource.WorkplaceDataSource
import com.example.glambooker.data.entity.Adress
import com.example.glambooker.data.entity.Filter
import com.example.glambooker.data.entity.Workplace
import com.example.glambooker.myApp.MyApplication

class WorkplaceRepository(val app: MyApplication){

    private val wds = WorkplaceDataSource()
    private val ads = AdressDataSource(app)

    fun uploadWorkplaces() : MutableLiveData<List<Workplace>> = wds.uploadWorkplaces()

    fun saveWorkplace(workplace: Workplace) = wds.saveWorkplace(workplace)

    fun uploadFilterWorkplacesFilter(filter: Filter) : List<Workplace> = wds.uploadFilterWorkplaces(filter = filter)

    suspend fun uploadCities() : List<Adress> = ads.getAdress()
}