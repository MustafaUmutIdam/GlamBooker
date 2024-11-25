package com.example.glambooker.data.repo

import com.example.glambooker.data.datasource.AdressDataSource
import com.example.glambooker.data.datasource.WorkplaceDataSource
import com.example.glambooker.data.entity.Adress
import com.example.glambooker.data.entity.Filter
import com.example.glambooker.data.entity.Workplace
import com.example.glambooker.myApp.MyApplication

class WorkplaceRepository(val app: MyApplication){

    val wds = WorkplaceDataSource()
    val ads = AdressDataSource(app)

    fun uploadWorkplaces() : List<Workplace> = wds.uploadWorkplaces()

    fun uploadFilterWorkplacesFilter(filter: Filter) : List<Workplace> = wds.uploadFilterWorkplaces(filter = filter)

    suspend fun uploadCities() : List<Adress> = ads.getAdress()
}