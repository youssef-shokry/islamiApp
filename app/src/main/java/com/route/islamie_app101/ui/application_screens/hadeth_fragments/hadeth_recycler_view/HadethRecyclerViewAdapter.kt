package com.route.islamie_app101.ui.application_screens.hadeth_fragments.hadeth_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.databinding.HadethCardItemBinding

class HadethRecyclerViewAdapter : RecyclerView.Adapter<HadethRecyclerViewAdapter.HadethViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadethViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = HadethCardItemBinding.inflate(inflater, parent, false)
        return HadethViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HadethViewHolder,
        position: Int
    ) {

    }

    override fun getItemCount(): Int = 5

    class HadethViewHolder(val binding: HadethCardItemBinding): RecyclerView.ViewHolder(binding.root)
}