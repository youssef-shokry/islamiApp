package com.route.islamie_app101.ui.application_screens.quran_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.route.islamie_app101.databinding.FragmentSelectSuraBinding
import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.SelectSuraRecyclerViewAdapter
import com.route.islamie_app101.ui.IslamiViewModel
import com.route.islamie_app101.ui.application_screens.quran_fragments.interfaces.SetOnRecentSuraClick
import com.route.islamie_app101.ui.application_screens.quran_fragments.interfaces.SetOnSuraClick
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.RecentSuraRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectSuraFragment : Fragment() {
    private lateinit var binding: FragmentSelectSuraBinding
    private lateinit var suraListAdapter: SelectSuraRecyclerViewAdapter
    private lateinit var recentSuraAdapter: RecentSuraRecyclerViewAdapter
    private val islamiViewModel: IslamiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectSuraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapters()
        initClickListeners()
        showMostRecent()
    }

    private fun initClickListeners() {
        suraListAdapter.setOnSuraClick = object : SetOnSuraClick {
            override fun onSuraClick(sura: SuraDataModel) {
                val action =
                    SelectSuraFragmentDirections.actionSelectSuraFragmentToSuraFragment(sura)
                findNavController().navigate(action)
                mostRecentSura(sura)
            }
        }

        recentSuraAdapter.setOnRecentSuraClick = SetOnRecentSuraClick { sura ->
            val action =
                SelectSuraFragmentDirections.actionSelectSuraFragmentToSuraFragment(sura)
            findNavController().navigate(action)
            mostRecentSura(sura)
        }
    }

    private fun setUpAdapters() {
        suraListAdapter = SelectSuraRecyclerViewAdapter(islamiViewModel.surasList)
        binding.surasRecyclerView.adapter = suraListAdapter

        recentSuraAdapter = RecentSuraRecyclerViewAdapter()
        binding.recentSurasRc.adapter = recentSuraAdapter
    }

    fun mostRecentSura(sura: SuraDataModel) {
        addRecentSura(sura)
        showMostRecent()
    }

    fun showMostRecent() {
        if (!islamiViewModel.loadRecentSuras().isEmpty()) {
            binding.mostRecentText.visibility = View.VISIBLE
            binding.recentSurasRc.visibility = View.VISIBLE
        }
        recentSuraAdapter.submitList(islamiViewModel.loadRecentSuras().toList())
    }

    fun addRecentSura(sura: SuraDataModel) {
        islamiViewModel.addRecentSura(sura)
        recentSuraAdapter.submitList(islamiViewModel.loadRecentSuras())
    }
}