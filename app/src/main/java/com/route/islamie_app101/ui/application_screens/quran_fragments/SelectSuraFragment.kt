package com.route.islamie_app101.ui.application_screens.quran_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.route.islamie_app101.databinding.FragmentSelectSuraBinding
import com.route.islamie_app101.domain.data_models.SuraDataModel
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.SelectSuraRecyclerViewAdapter
import com.route.islamie_app101.ui.ViewModel
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.SuraClick

class SelectSuraFragment : Fragment() {

    private lateinit var binding: FragmentSelectSuraBinding
    private lateinit var adapter: SelectSuraRecyclerViewAdapter
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
        initClickListeners()
    }

    private fun initClickListeners() {
        adapter.suraClick = object: SuraClick{
            override fun onSuraClick(sura: SuraDataModel) {
                val action = SelectSuraFragmentDirections.actionSelectSuraFragmentToSuraFragment(sura)

                findNavController().navigate(action)
            }
        }
    }

    private fun setUpAdapter() {
        adapter = SelectSuraRecyclerViewAdapter(viewModel.suraList)
        binding.surasRecyclerView.adapter = adapter
    }

}