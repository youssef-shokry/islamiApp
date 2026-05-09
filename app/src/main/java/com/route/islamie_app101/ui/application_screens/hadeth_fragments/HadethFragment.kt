package com.route.islamie_app101.ui.application_screens.hadeth_fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.route.islamie_app101.databinding.FragmentHadethBinding

class HadethFragment : Fragment() {
    private lateinit var binding: FragmentHadethBinding
    private val args by navArgs<HadethFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHadethBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.backArrowIcon.setOnClickListener {
            val action = HadethFragmentDirections.actionHadethFragmentToSelectHadethFragment()

            findNavController().navigate(action)
        }
    }

    private fun initViews() {
        binding.hadethTitle.text = args.hadeth.title

        binding.hadethContent.text = args.hadeth.content

        val hadethEnNumber = "Hadeth ${args.hadeth.id}"
        binding.hadethTitleEn.text = hadethEnNumber
    }

}