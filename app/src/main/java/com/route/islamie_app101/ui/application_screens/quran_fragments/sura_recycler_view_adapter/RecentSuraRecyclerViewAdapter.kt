package com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.databinding.RecentlySelectedItemBinding
import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import com.route.islamie_app101.ui.application_screens.quran_fragments.diff_util.RecentDiffUtil

class RecentSuraRecyclerViewAdapter :
    ListAdapter<SuraDataModel, RecentSuraRecyclerViewAdapter.RecentSuraViewHolder>(RecentDiffUtil()) {
    private lateinit var binding: RecentlySelectedItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSuraViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RecentlySelectedItemBinding.inflate(inflater, parent, false)
        return RecentSuraViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecentSuraViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class RecentSuraViewHolder(val binding: RecentlySelectedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sura: SuraDataModel) {
            binding.suraEnName.text = sura.suraNameEn
            binding.suraArName.text = sura.suraNameAr
            binding.suraVersesNumber.text = sura.versesNumber
        }
    }

}