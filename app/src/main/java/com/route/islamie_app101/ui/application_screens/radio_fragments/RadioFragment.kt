package com.route.islamie_app101.ui.application_screens.radio_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.islamie_app101.databinding.FragmentRadioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioFragment : Fragment() {
    private lateinit var binding: FragmentRadioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }
}