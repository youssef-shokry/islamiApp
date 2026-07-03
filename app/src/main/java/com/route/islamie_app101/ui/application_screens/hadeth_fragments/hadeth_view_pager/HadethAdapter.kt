package com.route.islamie_app101.ui.application_screens.hadeth_fragments.hadeth_view_pager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.databinding.HadethCardItemBinding
import com.route.islamie_app101.domain.data_models.HadethDataModel

class HadethAdapter(val ahadethList: List<HadethDataModel>) :
    RecyclerView.Adapter<HadethAdapter.HadethViewHolder>() {

    var hadethClick: HadethClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadethViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HadethCardItemBinding.inflate(inflater, parent, false)
        return HadethViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HadethViewHolder,
        position: Int
    ) {
        holder.bind(ahadethList[position])
    }

    override fun getItemCount(): Int = ahadethList.size

    inner class HadethViewHolder(val binding: HadethCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hadeth: HadethDataModel) {
            binding.hadethTitle.text = hadeth.title
            binding.hadethContent.text = hadeth.content
            onHadethClick(hadeth)
        }

        fun onHadethClick(hadeth: HadethDataModel){
            binding.root.setOnClickListener {
                hadethClick!!.onHadethClick(hadeth)
            }
        }
    }
}