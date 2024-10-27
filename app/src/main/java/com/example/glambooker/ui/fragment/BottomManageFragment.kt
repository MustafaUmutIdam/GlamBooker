package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.glambooker.R
import com.example.glambooker.databinding.FragmentBottomManageBinding


class BottomManageFragment : Fragment() {

    private lateinit var binding: FragmentBottomManageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBottomManageBinding.inflate(inflater,container,false)


        return binding.root
    }


}