package com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.databinding.SuraAyaItemBinding
class SuraRecyclerViewAdapter(val ayatList: List<String>) :
    RecyclerView.Adapter<SuraRecyclerViewAdapter.AyaViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AyaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SuraAyaItemBinding.inflate(inflater, parent, false)

        return AyaViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AyaViewHolder,
        position: Int
    ) {
        holder.bind(ayatList[position])
    }

    override fun getItemCount(): Int = ayatList.size


    class AyaViewHolder(val binding: SuraAyaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(aya: String) {
            binding.aya.text = aya
        }
    }
}