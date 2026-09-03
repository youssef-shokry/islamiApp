package com.route.islamie_app101.ui.application_screens.quran_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.route.islamie_app101.databinding.FragmentSelectSuraBinding
import com.route.islamie_app101.domain.data_models.sura.SuraDataModel
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.SelectSuraRecyclerViewAdapter
import com.route.islamie_app101.ui.IslamiViewModel
import com.route.islamie_app101.ui.application_screens.quran_fragments.interfaces.SetOnSuraClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectSuraFragment : Fragment() {

    private lateinit var binding: FragmentSelectSuraBinding
    private lateinit var adapter: SelectSuraRecyclerViewAdapter
    private val islamiViewModel: IslamiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectSuraBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initClickListeners() {
        adapter.setOnSuraClick = object : SetOnSuraClick {
            override fun onSuraClick(sura: SuraDataModel) {
                val action =
                    SelectSuraFragmentDirections.actionSelectSuraFragmentToSuraFragment(sura)
                findNavController().navigate(action)
            }
        }
    }

    private fun setUpAdapter() {
        adapter = SelectSuraRecyclerViewAdapter(islamiViewModel.surasList)
        binding.surasRecyclerView.adapter = adapter
    }

    private fun getPreviousPosition() {
        val layoutManager = binding.surasRecyclerView.layoutManager as LinearLayoutManager

        layoutManager.scrollToPositionWithOffset(
            islamiViewModel.surasListLastPosition, islamiViewModel.surasListLastPositionOffset
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAdapter()
        getPreviousPosition()
        initClickListeners()
    }

    private fun savePosition() {
        val layoutManager = binding.surasRecyclerView.layoutManager as LinearLayoutManager
        islamiViewModel.surasListLastPosition = layoutManager.findFirstVisibleItemPosition()

        val view = layoutManager.findViewByPosition(islamiViewModel.surasListLastPosition)
        islamiViewModel.surasListLastPositionOffset = view?.top ?: 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        savePosition()
    }
}