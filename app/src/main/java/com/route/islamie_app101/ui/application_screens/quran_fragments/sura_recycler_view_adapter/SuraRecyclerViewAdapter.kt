package com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.R
import com.route.islamie_app101.databinding.SuraAyaItemBinding

class SuraRecyclerViewAdapter(private val ayatList: List<String>) :
    RecyclerView.Adapter<SuraRecyclerViewAdapter.AyaViewHolder>() {

    var selectedPosition = RecyclerView.NO_POSITION

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
        holder.bind(ayatList[position], position)
    }

    override fun getItemCount(): Int = ayatList.size


    inner class AyaViewHolder(val binding: SuraAyaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(aya: String, position: Int) {
            binding.ayaText.text = aya
            ayaClick(binding, position)
        }

        fun ayaClick(binding: SuraAyaItemBinding, position: Int){
            val goldBackground =
                ContextCompat.getDrawable(binding.root.context, R.drawable.selected_aya_stroke)
            val goldStroke =
                ContextCompat.getDrawable(binding.root.context, R.drawable.ayat_stroke)
            val black =
                ContextCompat.getColor(binding.root.context, R.color.black)
            val gold =
                ContextCompat.getColor(binding.root.context, R.color.gold)

            if(position == selectedPosition){
                binding.root.background = goldBackground
                binding.ayaText.setTextColor(black)
            }else{
                binding.root.background = goldStroke
                binding.ayaText.setTextColor(gold)
            }

            binding.root.setOnClickListener {
                val oldPosition = selectedPosition

                if (selectedPosition == position) {
                    selectedPosition = RecyclerView.NO_POSITION
                }else{
                    selectedPosition = position
                }

                if (oldPosition != position){
                    notifyItemChanged(oldPosition)
                }
                if (selectedPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(selectedPosition)
                }
            }
        }

    }
}