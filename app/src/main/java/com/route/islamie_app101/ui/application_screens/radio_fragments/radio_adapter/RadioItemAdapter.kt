package com.route.islamie_app101.ui.application_screens.radio_fragments.radio_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.islamie_app101.databinding.RadioItemBinding

class RadioItemAdapter<T>(
    private var listItems: List<T> = emptyList(),
    private val bind: (radioItem: RadioItemBinding, listItem: T) -> Unit
) :
    RecyclerView.Adapter<RadioItemAdapter.ItemViewHolder>() {
    private lateinit var binding: RadioItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = RadioItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        bind(holder.binding, listItems[position])
    }

    override fun getItemCount(): Int = listItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newListItems: List<T>) {
        listItems = newListItems
        notifyDataSetChanged()
    }

    class ItemViewHolder(val binding: RadioItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}