package com.example.glambooker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.glambooker.data.entity.Workplace
import com.example.glambooker.databinding.FragmentBottomBookingBinding
import com.example.glambooker.databinding.WorkplacesCardDesignBinding

class WorkplaceAdapter(var mContext:Context,var workplaceList:List<Workplace>) :
    RecyclerView.Adapter<WorkplaceAdapter.WorkplaceCardDesignHolder>() {

    inner class WorkplaceCardDesignHolder(var design:WorkplacesCardDesignBinding) : RecyclerView.ViewHolder(design.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkplaceCardDesignHolder {
        val binding = WorkplacesCardDesignBinding.inflate(LayoutInflater.from(mContext),parent,false)
        return WorkplaceCardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkplaceCardDesignHolder, position: Int) {
        val workplace = workplaceList.get(position)
        val h = holder.design

        h.textViewWorkplaceName.text = workplace.name
        h.textViewWorkplaceDetail.text = workplace.detail
        h.textViewRate.text=workplace.rate
    }

    override fun getItemCount(): Int {
        return workplaceList.size
    }
}