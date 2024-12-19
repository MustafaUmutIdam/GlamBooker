package com.example.glambooker.data.datasource

import androidx.lifecycle.MutableLiveData
import com.example.glambooker.data.entity.Date
import com.example.glambooker.data.entity.Workplace
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore

class DateDataSource {
    var collectionDates: CollectionReference = Firebase.firestore.collection("Dates")
    var dateList = MutableLiveData<List<Date>>()

    fun saveDates(date:Date){
        val newDate = Date("${date.id}",
            "${date.phone}",
            "${date.dayPlace}",
            "${date.dayTime}",
            "${date.hourPlace}",
            "${date.hourTime}",
            "${date.hourStatus}",
            "${date.dateDetail}",)

        collectionDates.document().set(newDate)


    }

    fun uploadDates():MutableLiveData<List<Date>>{
        collectionDates.addSnapshotListener { value, error ->
            if (value != null){
                val list = ArrayList<Date>()

                for (d in value.documents){
                    val date = d.toObject(Date::class.java)
                    if (date!= null){
                        date.id = d.id
                        list.add(date)
                    }
                }
                dateList.value = list
            }
        }
        return dateList
    }
}