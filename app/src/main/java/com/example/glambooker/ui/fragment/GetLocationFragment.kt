package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.glambooker.R
import com.example.glambooker.databinding.FragmentGetLocationBinding

class GetLocationFragment : Fragment() {
    private lateinit var binding: FragmentGetLocationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View{
        binding = FragmentGetLocationBinding.inflate(inflater,container,false)

        return binding.root
    }

}