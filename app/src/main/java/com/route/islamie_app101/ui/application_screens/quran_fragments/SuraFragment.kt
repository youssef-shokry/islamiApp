package com.route.islamie_app101.ui.application_screens.quran_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.route.islamie_app101.databinding.FragmentSuraBinding

class SuraFragment : Fragment() {
    private lateinit var binding: FragmentSuraBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuraBinding.inflate(inflater, container, false)
        return binding.root
    }

}