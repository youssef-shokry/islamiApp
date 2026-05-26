package com.route.islamie_app101.ui.application_screens.quran_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.route.islamie_app101.R
import com.route.islamie_app101.data.utils.Constants.Companion.SURA_PATH
import com.route.islamie_app101.databinding.FragmentSuraBinding
import com.route.islamie_app101.databinding.SuraAyaItemBinding
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.AyaClick
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.SuraRecyclerViewAdapter

class SuraFragment : Fragment() {
    private lateinit var binding: FragmentSuraBinding
    private val args by navArgs<SuraFragmentArgs>()
    lateinit var adapter: SuraRecyclerViewAdapter
    private val ayatList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initRv()
        intiAyaList(args.sura.id.toInt())
        initClickListeners()
    }

    private fun initViews() {
        binding.suraNameAr.text = args.sura.suraNameAr
        binding.suraNameEn.text = args.sura.suraNameEn
    }

    private fun initClickListeners() {
        binding.backArrowIcon.setOnClickListener {
            val action = SuraFragmentDirections.actionSuraFragmentToSelectSuraFragment()

            findNavController().navigate(action)
        }
        onAyaClick()
    }

    private fun onAyaClick() {
        val goldBackground =
            ContextCompat.getDrawable(requireContext(), R.drawable.selected_aya_stroke)
        val black = ContextCompat.getColor(requireContext(), R.color.black)

        var isItemSelected = false

        adapter.ayaClick = object : AyaClick {
            override fun onAyaClick(aya: SuraAyaItemBinding) {
                aya.root.background = goldBackground
                aya.ayaText.setTextColor(black)
            }
        }
    }

    private fun initRv() {
        adapter = SuraRecyclerViewAdapter(ayatList)
        binding.ayatRecyclerView.adapter = adapter
    }

    private fun intiAyaList(id: Int): List<String> {
        val inputStream = requireContext().assets.open("${SURA_PATH}${id}.txt")
        val reader = inputStream.bufferedReader()

        var line = reader.readLine()

        var index = 1
        while (line != null) {
            if (line == "") {
                line = reader.readLine()
                continue
            }
            ayatList.add("[$index] ${line.trim()}")
            line = reader.readLine()
            index++
        }
        return ayatList
    }

}