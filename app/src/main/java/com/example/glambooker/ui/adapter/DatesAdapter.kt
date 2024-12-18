package com.example.glambooker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.glambooker.databinding.DateCardDesignBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class DatesAdapter(var mcontext : Context, private val days : List<String>) :
    RecyclerView.Adapter<DatesAdapter.DateCardDesignHolder>() {
    private val expandedPositions = mutableSetOf<Int>()

    inner class DateCardDesignHolder(var design: DateCardDesignBinding) : RecyclerView.ViewHolder(design.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateCardDesignHolder {
        val binding = DateCardDesignBinding.inflate(LayoutInflater.from(mcontext),parent,false)
        return DateCardDesignHolder(binding)
    }

    //Fonksiyonel islemler
    override fun onBindViewHolder(holder: DateCardDesignHolder, position: Int) {
        val day = days.get(position)
        val h = holder.design

        h.textViewDay.text=day

        val chipGroup : ChipGroup = h.chipHour

        //Card'in icindeki saat Chipleri olusuyor
        if (chipGroup.childCount == 0 ){
            for (hour in 12..23){
                val chip = Chip(chipGroup.context)
                chip.text = "$hour:00"
                chipGroup.addView(chip)
            }
        }
        //Ilk buildde nasıl gözüktügü
        if (expandedPositions.contains(position)) {
            h.textViewDay.visibility = View.GONE
            chipGroup.visibility = View.VISIBLE
        } else {
            h.textViewDay.visibility = View.VISIBLE
            chipGroup.visibility = View.GONE
        }

        //Card'a tıklayinca degisim
        h.cVDay.setOnClickListener{
            if (expandedPositions.contains(position)){
                expandedPositions.remove(position)
            }
            else{
                expandedPositions.add(position)
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }


}