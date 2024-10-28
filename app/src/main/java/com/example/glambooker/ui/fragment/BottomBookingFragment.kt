package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding = FragmentBottomBookingBinding.inflate(inflater,container,false)

        binding.rv.layoutManager = LinearLayoutManager(requireContext())

        val workplaceList = ArrayList<Workplace>()
        val w1 = Workplace(1,"İsmail Erdamar Barber", "Saç/Sakal/Bakım","4.5","berber_image")
        val w2 = Workplace(2,"İsmail Erdamar Barber", "Saç/Sakal/Bakım","4.5","berber_image")
        val w3 = Workplace(3,"İsmail Erdamar Barber", "Saç/Sakal/Bakım","4.5","berber_image")
        workplaceList.add(w1)
        workplaceList.add(w2)
        workplaceList.add(w3)
        workplaceList.add(w1)
        workplaceList.add(w2)
        workplaceList.add(w3)
        workplaceList.add(w1)
        workplaceList.add(w2)
        workplaceList.add(w3)


        val workplaceAdapter = WorkplaceAdapter(requireContext(),workplaceList)
        binding.rv.adapter = workplaceAdapter

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:BottomBookingViewModel by viewModels()
        viewModel = tempViewModel
    }

}