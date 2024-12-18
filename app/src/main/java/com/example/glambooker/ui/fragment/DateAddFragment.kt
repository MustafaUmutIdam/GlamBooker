package com.example.glambooker.ui.fragment

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.glambooker.databinding.FragmentDateAddBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class DateAddFragment : Fragment() {
    private lateinit var binding:FragmentDateAddBinding
    var hourList = mutableListOf<String>()
    lateinit var formattedDate:String
    lateinit var formattedTime:String
    lateinit var stringArray:String

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
            }
            timePicker.show(parentFragmentManager,"MaterialTimePicker")
        }
        binding.buttonAddDay.setOnClickListener{
            Toast.makeText(requireContext(), "Gün Kaydedildi!:$formattedDate , $stringArray", Toast.LENGTH_LONG)
                .show()
        }


    }
}