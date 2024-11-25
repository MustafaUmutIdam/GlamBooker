package com.example.glambooker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.glambooker.R
import com.example.glambooker.data.entity.Workplace
import com.example.glambooker.databinding.FragmentBottomBookingBinding
import com.example.glambooker.databinding.WorkplacesCardDesignBinding
import com.example.glambooker.ui.fragment.BottomBookingFragmentDirections

class WorkplaceAdapter(var mContext:Context,var workplaceList:List<Workplace>) :
    RecyclerView.Adapter<WorkplaceAdapter.WorkplaceCardDesignHolder>() {

    inner class WorkplaceCardDesignHolder(var design:WorkplacesCardDesignBinding) : RecyclerView.ViewHolder(design.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkplaceCardDesignHolder {
        val binding:WorkplacesCardDesignBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.workplaces_card_design,parent,false)
        return WorkplaceCardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkplaceCardDesignHolder, position: Int) {
        val workplace = workplaceList.get(position)
        val h = holder.design

        h.workplace = workplace

        h.cardViewWorkplace.setOnClickListener{
            val transition = BottomBookingFragmentDirections.bottomtoBookingDetail(workplace = workplace)
            Navigation.findNavController(it).navigate(transition)
        }
    }

    override fun getItemCount(): Int {
        return workplaceList.size
    }
}