package com.example.glambooker.ui.fragment

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.glambooker.data.entity.Date
import com.example.glambooker.databinding.FragmentDateAddBinding
import com.example.glambooker.ui.viewmodel.BeBossViewModel
import com.example.glambooker.ui.viewmodel.DateAddViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class DateAddFragment : Fragment() {
    private lateinit var binding:FragmentDateAddBinding
    private lateinit var viewModel: DateAddViewModel
    var hourList = mutableListOf<String>()
    lateinit var formattedDate:String
    lateinit var formattedTime:String
    lateinit var stringArray:String
    var counter = 0
    var dayCounter = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentDateAddBinding.inflate(inflater, container, false)

        return binding.root
    }
    //Burdaki yapilar gec olusabilecegi icin onViewCreated icine yazildi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Gün Ay secme
        binding.buttonDatePicker.setOnClickListener{
            //MaterialPicker olusturma
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih Seç")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            //Tarihi secince
            datePicker.addOnPositiveButtonClickListener { timestamp ->
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = timestamp

                 val selectedDay = calendar.get(Calendar.DAY_OF_MONTH)
                 val selectedMonth = calendar.get(Calendar.MONTH)+1
                formattedDate = "$selectedDay/$selectedMonth"

                binding.textViewSelectedDate.text = "Secilen tarih : $formattedDate"
            }
            datePicker.show(parentFragmentManager,"MaterialDatePicker")
        }
        //Saat Secme
        binding.buttonTimePicker.setOnClickListener{
            //Saat Secici
            val timePicker = MaterialTimePicker.Builder()
                .setTitleText("Saat Seç")
                .setHour(12)
                .setMinute(0)
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            //Saat secilince
            timePicker.addOnPositiveButtonClickListener {

                val selectedHour = timePicker.hour
                val selectedMinute = timePicker.minute
                formattedTime = String.format("%02d:%02d",selectedHour,selectedMinute)

                hourList.add(formattedTime)

                stringArray = hourList.joinToString(separator = ", ")

                binding.textViewSelectedTime.text = stringArray

                val chipGroup = binding.cGroupHours

                    //Her saat icin chip olusumu
                    val chip = Chip(context)
                    chip.text = hourList[counter]
                    chipGroup.addView(chip)
                    counter +=1
            }
            timePicker.show(parentFragmentManager,"MaterialTimePicker")
        }

        //Günü kaydetmek icin nesne olusurup gönderildi!!!
        binding.buttonAddDay.setOnClickListener{
            var i = 0

            for(d in hourList){
                val date = Date("",
                "phone0538",
                "$dayCounter",
                "$formattedDate",
                "$i",
                "$d",
                "available",
                "")
                i++
                viewModel.saveDates(date)
                Log.e("GünKaydet","${date.toString()}")

            }

            Toast.makeText(requireContext(), "Gün Kaydedildi!:$formattedDate , $stringArray", Toast.LENGTH_LONG)
                .show()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DateAddViewModel by viewModels()
        viewModel = tempViewModel

    }
}