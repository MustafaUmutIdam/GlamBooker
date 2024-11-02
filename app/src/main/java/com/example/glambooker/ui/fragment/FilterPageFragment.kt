package com.example.glambooker.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.glambooker.databinding.FragmentFilterPageBinding
import com.example.glambooker.ui.viewmodel.BottomBookingViewModel
import com.example.glambooker.ui.viewmodel.FilterPageViewModel


class FilterPageFragment : Fragment() {
    private lateinit var binding: FragmentFilterPageBinding
    private lateinit var  viewModel: FilterPageViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFilterPageBinding.inflate(inflater,container,false)

        viewModel.citiesList.observe(viewLifecycleOwner){ list -> list?.let {
                val cities = ArrayList<String>()
                cities.add(it[0].city)
                cities.add(it[1].city)
                cities.add(it[2].city)
                val arrayAdapter =ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,cities )
                binding.autoCompleteCities.setAdapter(arrayAdapter)
            }

        }




        viewModel.citiesList.observe(viewLifecycleOwner){ list -> list?.let {
                val towns = ArrayList<String>()
                towns.add(it[0].town)
                towns.add(it[1].town)
                towns.add(it[25].town)
            Log.d("ExcelData", "City: $it, Town: ")
                val arrayAdapter =ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,towns )
                binding.autoCompleteTowns.setAdapter(arrayAdapter)
            }

        }

        val categories = ArrayList<String>()
        categories.add("Berberler")
        categories.add("Güzellik Salonları")
        val arrayAdapter =ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,categories )
        binding.autoCompleteCategories.setAdapter(arrayAdapter)




        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FilterPageViewModel by viewModels()
        viewModel = tempViewModel
    }

}