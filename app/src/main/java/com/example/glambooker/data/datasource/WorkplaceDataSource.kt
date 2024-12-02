package com.example.glambooker.data.datasource

import androidx.lifecycle.MutableLiveData
import com.example.glambooker.data.entity.Filter
import com.example.glambooker.data.entity.Workplace
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore

class WorkplaceDataSource {
    var collectionWorkplace:CollectionReference = Firebase.firestore.collection("Workplace")
    var workplaceList = MutableLiveData<List<Workplace>>()

    fun uploadWorkplaces() : MutableLiveData<List<Workplace>>{
        collectionWorkplace.addSnapshotListener { value, error ->
            if (value != null){
                val list = ArrayList<Workplace>()

                for (d in value.documents){
                    val workplace = d.toObject(Workplace::class.java)
                    if (workplace!= null){
                        workplace.id = d.id
                        list.add(workplace)
                    }
                }
                workplaceList.value = list
            }

        }
        return workplaceList
    }
    fun saveWorkplace(workplace: Workplace){
        val newWorkplace = Workplace("",
            workplace.boss.toString(),
            workplace.category.toString(),
            workplace.name.toString(),
            workplace.detail.toString(),
            workplace.city.toString(),
            workplace.town.toString(),
            workplace.rate.toString(),
            workplace.imageName.toString())
        collectionWorkplace.document().set(newWorkplace)
    }



    fun uploadFilterWorkplaces(filter: Filter) : List<Workplace> {

        val workplaceList = ArrayList<Workplace>()
        val w1 = Workplace(
            "1",
            "true",
            "Berber",
            "İsmail Erdamar Barber",
            "Saç/Sakal/Bakım",
            "Adana",
            "Seyhan",
            "4.5",
            "berber_image"
        )
        val w2 = Workplace(
            "2",
            "true",
            "Berber",
            "İsmail Erdamar Barber",
            "Saç/Sakal/Bakım",
            "Kayseri",
            "Develi",
            "4.5",
            "berber_image"
        )
        val w3 = Workplace(
            "3",
            "true",
            "Berber",
            "İsmail Erdamar Barber",
            "Saç/Sakal/Bakım",
            "Kayseri",
            "Develi",
            "4.5",
            "berber_image"
        )
        workplaceList.add(w1)
        workplaceList.add(w2)
        workplaceList.add(w3)
        workplaceList.add(w1)
        workplaceList.add(w2)
        workplaceList.add(w3)
        workplaceList.add(w1)
        workplaceList.add(w2)
        workplaceList.add(w3)
        val filterList = ArrayList<Workplace>()
        for (i in workplaceList.indices)
        {   if (workplaceList[i].city == filter.cityFilter
                && workplaceList[i].town == filter.townFilter
                && workplaceList[i].category == filter.categoryFilter){
                filterList.add(workplaceList[i])
               }
        }
        return filterList
    }

}