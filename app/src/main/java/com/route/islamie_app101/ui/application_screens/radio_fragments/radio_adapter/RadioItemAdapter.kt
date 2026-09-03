package com.route.islamie_app101.ui.application_screens.radio_fragments.radio_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.databinding.RadioItemBinding
import com.route.islamie_app101.domain.data_models.radio.diff_util.DiffIdentifiable
import com.route.islamie_app101.ui.application_screens.radio_fragments.radio_adapter.diff_util.DiffItemCallback

class RadioItemAdapter<T : DiffIdentifiable>(
    private val bind: (radioItem: RadioItemBinding, listItem: T?) -> Unit
) : ListAdapter<T, RadioItemAdapter.ItemViewHolder>(DiffItemCallback()) {
    private lateinit var binding: RadioItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RadioItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        bind(holder.binding, getItem(position))

    class ItemViewHolder(val binding: RadioItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}