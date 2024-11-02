package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glambooker.R
import com.example.glambooker.data.entity.Workplace
import com.example.glambooker.databinding.FragmentBottomBookingBinding
import com.example.glambooker.ui.adapter.WorkplaceAdapter
import com.example.glambooker.ui.viewmodel.BottomBookingViewModel

class BottomBookingFragment : Fragment() {
    private lateinit var binding: FragmentBottomBookingBinding
    private lateinit var viewModel : BottomBookingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_booking,container,false)

        binding.categoriesText = "Adana/Seyhan/Berberler"

        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.workplaceList.observe(viewLifecycleOwner){            //it = workplaceList
            val workplaceAdapter = WorkplaceAdapter(requireContext(),it)
            binding.workplaceAdapter = workplaceAdapter

        }

        binding.imageViewProfile.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.bottomBookingToFilterPage)
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:BottomBookingViewModel by viewModels()
        viewModel = tempViewModel
    }

}