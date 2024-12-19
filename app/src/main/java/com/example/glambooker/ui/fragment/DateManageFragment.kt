package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glambooker.R
import com.example.glambooker.data.entity.Date
import com.example.glambooker.databinding.FragmentDateManageBinding
import com.example.glambooker.ui.adapter.DatesAdapter
import com.example.glambooker.ui.viewmodel.DateManageViewModel


class DateManageFragment : Fragment() {
    private lateinit var binding: FragmentDateManageBinding
    private lateinit var viewModel: DateManageViewModel
    private lateinit var adapter: DatesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentDateManageBinding.inflate(inflater,container,false)

        binding.rV.layoutManager = LinearLayoutManager(requireContext())

        //DateList alma
        viewModel.dateList.observe(viewLifecycleOwner) {dateList ->
            adapter = DatesAdapter(requireContext(), dateList)
            binding.rV.adapter = adapter
        }


        //Otomatik günlere yenisi ekleniyor
        binding.floatingActionButton.setOnClickListener(){
            Navigation.findNavController(it).navigate(R.id.dateManageToDateAdd)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DateManageViewModel by viewModels()
        viewModel = tempViewModel

    }

}