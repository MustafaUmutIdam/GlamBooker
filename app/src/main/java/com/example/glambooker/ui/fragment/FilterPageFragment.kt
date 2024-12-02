package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.glambooker.data.entity.Adress
import com.example.glambooker.data.entity.Filter
import com.example.glambooker.databinding.FragmentFilterPageBinding
import com.example.glambooker.ui.viewmodel.FilterPageViewModel


class FilterPageFragment : Fragment() {
    private lateinit var binding: FragmentFilterPageBinding
    private lateinit var  viewModel: FilterPageViewModel
    private var selectedCity: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): ConstraintLayout {

        binding = FragmentFilterPageBinding.inflate(inflater, container, false)

        viewModel.adressList.observe(viewLifecycleOwner) { list ->
            list?.let {
                val cities = ArrayList<String>()
                //İlk sehir temp'e eklendi ,temp listeye eklendi , dosyada sıradaki sehirle aynı olmayana kadar ekleme olmadı
                var temp = it[0].city
                cities.add(temp)
                for (a in it) {
                    if (temp != a.city) {
                        temp = a.city
                        cities.add(temp)
                    }
                }

                val arrayAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    cities
                )
                binding.autoCompleteCities.setAdapter(arrayAdapter)
                //Seçilen şehri burda almalıyım
                binding.autoCompleteCities.setOnItemClickListener { _, _, position, _ ->
                    selectedCity = arrayAdapter.getItem(position)
                    Toast.makeText(requireContext(), selectedCity, Toast.LENGTH_LONG).show()
                    selectedCity?.let { city -> updateTowns(city, it) }
                }
            }
        }

        val categories = ArrayList<String>()
        categories.add("Berber")
        categories.add("Güzellik Salonları")
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories)
        binding.autoCompleteCategories.setAdapter(arrayAdapter)

        binding.buttonSearch.setOnClickListener {
            val cityFilter = binding.autoCompleteCities.text.toString()
            val townFilter = binding.autoCompleteTowns.text.toString()
            val categoryFilter = binding.autoCompleteCategories.text.toString()
            if (cityFilter.isEmpty() || townFilter.isEmpty() || categoryFilter.isEmpty()) {
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun!", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            val filter = Filter(cityFilter, townFilter, categoryFilter)
            val transition = FilterPageFragmentDirections.filterToBottomBooking(filter)
            Navigation.findNavController(it).navigate(transition)
        }

        return binding.root
    }

    private fun updateTowns(selectedCity :String, cityDistrictList: List<Adress>) {
        val towns = cityDistrictList.filter { it.city == selectedCity }.map { it.town }
        val townAdapter= ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,towns)
        binding.autoCompleteTowns.setAdapter(townAdapter)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FilterPageViewModel by viewModels()
        viewModel = tempViewModel
    }

}