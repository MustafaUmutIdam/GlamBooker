package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.glambooker.databinding.FragmentBottomVipBinding
import com.example.glambooker.ui.viewmodel.BottomVIPViewModel


class BottomVIPFragment : Fragment() {

    private lateinit var binding: FragmentBottomVipBinding
    private lateinit var viewModel : BottomVIPViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {

        binding = FragmentBottomVipBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BottomVIPViewModel by viewModels()
        viewModel = tempViewModel
    }
}