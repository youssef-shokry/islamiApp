package com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.R
import com.route.islamie_app101.databinding.SuraListItemBinding
import com.route.islamie_app101.domain.data_models.SuraDataModel

class SuraRecyclerViewAdapter(val surahsList: List<SuraDataModel>):
    RecyclerView.Adapter<SuraRecyclerViewAdapter.SuraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SuraListItemBinding.inflate(inflater, parent, false)
        return SuraViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuraViewHolder, position: Int) {
        holder.bind(surahsList[position])
    }

    override fun getItemCount(): Int {
        return surahsList.size
    }

    class SuraViewHolder(val binding: SuraListItemBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(sura: SuraDataModel){
            binding.suraNameEn.text = sura.suraNameEn
            binding.suraNameAr.text = sura.suraNameAr
            binding.suraVersesEn.text = itemView.context
                .getString(R.string.verses_count, sura.versesNumber.toInt())
            binding.suraNumber.text = sura.id
        }

    }
}