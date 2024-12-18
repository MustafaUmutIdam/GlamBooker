package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glambooker.R
import com.example.glambooker.databinding.FragmentDateManageBinding
import com.example.glambooker.ui.adapter.DatesAdapter


class DateManageFragment : Fragment() {
    private lateinit var binding:FragmentDateManageBinding
    private var days = mutableListOf<String>()
    private lateinit var adapter: DatesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentDateManageBinding.inflate(inflater,container,false)

        adapter = DatesAdapter(requireContext(),days)
        binding.rV.layoutManager = LinearLayoutManager(requireContext())
        binding.rV.adapter = adapter



        //Günler otomatik olusuyor
        for(i in 1..15) {
            days.add("Gün $i")
        }
        adapter.notifyDataSetChanged()

        //Otomatik günlere yenisi ekleniyor
        binding.floatingActionButton.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.dateManageToDateAdd)
            val newDay = "Gün ${days.size + 1}"
            days.add(newDay)
            adapter.notifyItemInserted(days.size - 1 )
        }

        return binding.root
    }


}