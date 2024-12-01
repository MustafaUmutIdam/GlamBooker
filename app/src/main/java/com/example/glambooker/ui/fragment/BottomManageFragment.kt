package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.glambooker.R
import com.example.glambooker.databinding.FragmentBottomManageBinding
import com.example.glambooker.ui.viewmodel.BottomManageViewModel


class BottomManageFragment : Fragment() {

    private lateinit var binding: FragmentBottomManageBinding
    private lateinit var viewModel : BottomManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_manage,container,false)

        binding.manageText = "İŞLETMELERİ YÖNET"
        binding.beBossText = "PATRON OL"
        binding.beVIPText = "VIP OL"
        binding.aboutUsText = "HAKKIMIZDA"

        binding.chipBeBoss.setOnClickListener {
        Navigation.findNavController(it).navigate(R.id.bottomManageToBeBoss)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:BottomManageViewModel by viewModels()
        viewModel = tempViewModel
    }

}