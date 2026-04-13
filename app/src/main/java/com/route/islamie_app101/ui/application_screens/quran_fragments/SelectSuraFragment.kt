package com.route.islamie_app101.ui.application_screens.quran_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.islamie_app101.data.repositorys.quran_repository.ImpQuranRepository
import com.route.islamie_app101.databinding.FragmentSelectSuraBinding
import com.route.islamie_app101.domain.repository.quran_repository.QuranReository
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.SuraRecyclerViewAdapter

class SelectSuraFragment : Fragment() {

    lateinit var binding: FragmentSelectSuraBinding
    lateinit var adapter: SuraRecyclerViewAdapter

    private val repo: QuranReository = ImpQuranRepository()

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
        adapter = SuraRecyclerViewAdapter(repo.getSurahsList())
    }

}