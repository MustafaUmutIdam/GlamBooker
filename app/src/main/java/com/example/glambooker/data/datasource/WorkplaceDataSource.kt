package com.example.glambooker.data.datasource

import com.example.glambooker.data.entity.Adress
import com.example.glambooker.data.entity.Workplace

class WorkplaceDataSource {

    fun uploadWorkplaces() : List<Workplace>{


        val workplaceList = ArrayList<Workplace>()
        val w1 = Workplace(1,"İsmail Erdamar Barber", "Saç/Sakal/Bakım","Kayseri","Develi","4.5","berber_image")
        val w2 = Workplace(2,"İsmail Erdamar Barber", "Saç/Sakal/Bakım","Kayseri","Develi","4.5","berber_image")
        val w3 = Workplace(3,"İsmail Erdamar Barber", "Saç/Sakal/Bakım","Kayseri","Develi","4.5","berber_image")
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




}