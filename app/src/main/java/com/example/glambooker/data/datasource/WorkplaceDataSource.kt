package com.example.glambooker.data.datasource

import com.example.glambooker.data.entity.Adress
import com.example.glambooker.data.entity.Filter
import com.example.glambooker.data.entity.Workplace

class WorkplaceDataSource {

    fun uploadWorkplaces() : List<Workplace>{


        val workplaceList = ArrayList<Workplace>()
        val w1 = Workplace(1,"Berber","İsmail Erdamar Barber", "Saç/Sakal/Bakım","Kayseri","Develi","4.5","berber_image")
        val w2 = Workplace(2,"Berber","İsmail Erdamar Barber", "Saç/Sakal/Bakım","Kayseri","Develi","4.5","berber_image")
        val w3 = Workplace(3,"Berber","İsmail Erdamar Barber", "Saç/Sakal/Bakım","Kayseri","Develi","4.5","berber_image")
        workplaceList.add(w1)
        workplaceList.add(w2)
        workplaceList.add(w3)
        workplaceList.add(w1)
        workplaceList.add(w2)
        workplaceList.add(w3)
        workplaceList.add(w1)
        workplaceList.add(w2)
        workplaceList.add(w3)
        return workplaceList
    }

    fun uploadFilterWorkplaces(filter: Filter) : List<Workplace> {

        val workplaceList = ArrayList<Workplace>()
        val w1 = Workplace(
            1,
            "Berber",
            "İsmail Erdamar Barber",
            "Saç/Sakal/Bakım",
            "Adana",
            "Seyhan",
            "4.5",
            "berber_image"
        )
        val w2 = Workplace(
            2,
            "Berber",
            "İsmail Erdamar Barber",
            "Saç/Sakal/Bakım",
            "Kayseri",
            "Develi",
            "4.5",
            "berber_image"
        )
        val w3 = Workplace(
            3,
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
        {   if (workplaceList[i].city == filter.cityFİlter
                && workplaceList[i].town == filter.townFilter
                && workplaceList[i].category == filter.categoryFilter){
                filterList.add(workplaceList[i])
               }
        }
        return filterList
    }



}