package com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.databinding.SuraAyaItemBinding
import com.route.islamie_app101.ui.application_screens.quran_fragments.interfaces.SetOnAyaClick

class SuraRecyclerViewAdapter (private val ayatList: List<String>) :
    RecyclerView.Adapter<SuraRecyclerViewAdapter.AyaViewHolder>() {
    private lateinit var binding: SuraAyaItemBinding
    var setOnAyaClick: SetOnAyaClick? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AyaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = SuraAyaItemBinding.inflate(inflater, parent, false)
        return AyaViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AyaViewHolder,
        position: Int
    ) {
        holder.bind(ayatList[position], position)
    }

    override fun getItemCount(): Int = ayatList.size

    fun updateSelection(oldPosition: Int, selectedPosition: Int) {
        if (oldPosition != -1) {
            notifyItemChanged(oldPosition)
        }

        if (selectedPosition != -1) {
            notifyItemChanged(selectedPosition)
        }
    }

    inner class AyaViewHolder(val binding: SuraAyaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(aya: String, position: Int) {
            binding.ayaText.text = aya
            setOnAyaClick?.onAyaClick(binding, position)
        }
    }
}