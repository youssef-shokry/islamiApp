package com.route.islamie_app101.ui.application_screens.radio_fragments.radio_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.databinding.RadioViewpagerPageBinding
import com.route.islamie_app101.domain.data_models.radio.RadioDataModel
import com.route.islamie_app101.domain.data_models.radio.ReciterDataModel

class RadioPagerAdapter(
    private val radioAdapter: RadioItemAdapter<RadioDataModel>,
    private val recitersAdapter: RadioItemAdapter<ReciterDataModel>
) : RecyclerView.Adapter<RadioPagerAdapter.RadioViewHolder>() {
    private lateinit var binding: RadioViewpagerPageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RadioViewpagerPageBinding.inflate(inflater, parent, false)

        return RadioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val adapter = when (position) {
            0 -> radioAdapter
            1 -> recitersAdapter
            else -> throw IllegalStateException("Unexpected position $position")
        }
        if (holder.binding.radiosList.adapter !== adapter) {
            holder.binding.radiosList.adapter = adapter
        }
    }

    override fun getItemCount(): Int = 2

    class RadioViewHolder(val binding: RadioViewpagerPageBinding) :
        RecyclerView.ViewHolder(binding.root)
}