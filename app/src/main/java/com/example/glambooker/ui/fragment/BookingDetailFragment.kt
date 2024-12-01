package com.example.glambooker.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.glambooker.databinding.FragmentBookingDetailBinding


class BookingDetailFragment : Fragment() {

    private lateinit var binding: FragmentBookingDetailBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentBookingDetailBinding.inflate(inflater,container,false)

        val bundle : BookingDetailFragmentArgs by navArgs()
        val workplace = bundle.workplace

        binding.detailInfo.text = workplace.toString()


        return binding.root
    }

}