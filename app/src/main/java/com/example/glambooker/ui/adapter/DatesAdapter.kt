package com.example.glambooker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.glambooker.data.entity.Date
import com.example.glambooker.databinding.DateCardDesignBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class DatesAdapter(
    private val context: Context,
    private val datesList: List<Date>
) : RecyclerView.Adapter<DatesAdapter.DateCardDesignHolder>() {

    private val expandedPositions = mutableSetOf<Int>()

    // Gruplama işlemi: Aynı dayPlace değerine sahip Date nesnelerini bir araya getirme
    private val groupedDates = datesList.groupBy { it.dayPlace }

    inner class DateCardDesignHolder(val binding: DateCardDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateCardDesignHolder {
        val binding = DateCardDesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return DateCardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: DateCardDesignHolder, position: Int) {
        val dayPlace = groupedDates.keys.toList()[position]
        val dateListForPlace = groupedDates[dayPlace] ?: emptyList()

        val binding = holder.binding
        binding.textViewDay.text = datesList[position].dayTime

        // ChipGroup'i temizle (önceki Chip'leri kaldır)
        val chipGroup = binding.chipHour
        chipGroup.removeAllViews()

        // Her dayPlace için hourTime'ları ekle
        for (date in dateListForPlace) {
            date.hourTime?.let { hourTime ->
                val chip = Chip(context).apply {
                    text = hourTime
                }
                chipGroup.addView(chip)
            }
        }

        // İlk durum - Card görünümü
        if (expandedPositions.contains(position)) {
            binding.textViewDay.visibility = View.GONE
            chipGroup.visibility = View.VISIBLE
        } else {
            binding.textViewDay.visibility = View.VISIBLE
            chipGroup.visibility = View.GONE
        }

        // Card tıklama davranışı
        binding.cVDay.setOnClickListener {
            if (expandedPositions.contains(position)) {
                expandedPositions.remove(position)
            } else {
                expandedPositions.add(position)
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return groupedDates.size
    }
}
