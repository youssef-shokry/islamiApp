package com.route.islamie_app101.ui.application_screens.quran_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.route.islamie_app101.databinding.FragmentSelectSuraBinding
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.SuraRecyclerViewAdapter
import com.route.islamie_app101.ui.ViewModel

class SelectSuraFragment : Fragment() {

    private lateinit var binding: FragmentSelectSuraBinding
    private lateinit var adapter: SuraRecyclerViewAdapter
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectSuraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapter()
    }

    private fun setUpAdapter() {
        adapter = SuraRecyclerViewAdapter(viewModel.suraList)
        binding.surasRecyclerView.adapter = adapter
    }

}