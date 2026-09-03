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
import com.route.islamie_app101.utils.Constants.Companion.SURA_PATH
import com.route.islamie_app101.databinding.FragmentSuraBinding
import com.route.islamie_app101.databinding.SuraAyaItemBinding
import com.route.islamie_app101.ui.application_screens.quran_fragments.interfaces.SetOnAyaClick
import com.route.islamie_app101.ui.application_screens.quran_fragments.sura_recycler_view_adapter.SuraRecyclerViewAdapter

class SuraFragment : Fragment() {
    private lateinit var binding: FragmentSuraBinding
    private val args by navArgs<SuraFragmentArgs>()
    lateinit var adapter: SuraRecyclerViewAdapter
    private val ayatList: MutableList<String> = mutableListOf()
    private  var selectedAyaPosition: Int = -1

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
        initAyaList(args.sura.id.toInt())
        initClickListeners()
        }

    private fun initViews() {
        binding.suraNameAr.text = args.sura.suraNameAr
        binding.suraNameEn.text = args.sura.suraNameEn
    }

    private fun initRv() {
        adapter = SuraRecyclerViewAdapter(ayatList)
        binding.ayatRecyclerView.adapter = adapter
    }

    private fun initAyaList(id: Int): List<String> {
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
    private fun initClickListeners() {
        binding.backArrowIcon.setOnClickListener {
            val action = SuraFragmentDirections.actionSuraFragmentToSelectSuraFragment()

            findNavController().navigate(action)
        }

        adapter.setOnAyaClick = object : SetOnAyaClick {
            override fun onAyaClick(binding: SuraAyaItemBinding, position: Int) {

                binding.root.setOnClickListener {
                    val oldPosition = selectedAyaPosition

                    selectedAyaPosition = if (selectedAyaPosition == position) {
                        -1
                    } else {
                        position
                    }
                    adapter.updateSelection(oldPosition, selectedAyaPosition)
                }

                ayaEffect(binding, position)
            }
        }
    }
    private fun ayaEffect(ayaBinding: SuraAyaItemBinding, position: Int){
        val goldBackground =
            ContextCompat.getDrawable(requireContext(), R.drawable.selected_aya_stroke)

        val goldStroke =
            ContextCompat.getDrawable(requireContext(), R.drawable.ayat_stroke)

        val black = ContextCompat.getColor(requireContext(), R.color.black)

        val gold = ContextCompat.getColor(requireContext(), R.color.gold)

        if (position == selectedAyaPosition) {
            ayaBinding.root.background = goldBackground
            ayaBinding.ayaText.setTextColor(black)
        } else {
            ayaBinding.root.background = goldStroke
            ayaBinding.ayaText.setTextColor(gold)
        }
    }

}