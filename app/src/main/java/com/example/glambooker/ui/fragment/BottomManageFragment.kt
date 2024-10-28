package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.glambooker.R
import com.example.glambooker.databinding.FragmentBottomManageBinding
import com.example.glambooker.ui.viewmodel.BottomBookingViewModel
import com.example.glambooker.ui.viewmodel.BottomManageViewModel


class BottomManageFragment : Fragment() {

    private lateinit var binding: FragmentBottomManageBinding
    private lateinit var viewModel : BottomManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBottomManageBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:BottomManageViewModel by viewModels()
        viewModel = tempViewModel
    }

}