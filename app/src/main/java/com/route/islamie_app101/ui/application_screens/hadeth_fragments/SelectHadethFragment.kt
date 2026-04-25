package com.route.islamie_app101.ui.application_screens.hadeth_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.route.islamie_app101.databinding.FragmentSelectHadethBinding
import com.route.islamie_app101.ui.application_screens.hadeth_fragments.hadeth_recycler_view.HadethRecyclerViewAdapter
import kotlin.math.abs


class SelectHadethFragment : Fragment() {

    private lateinit var binding: FragmentSelectHadethBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectHadethBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVp2()
        initTransformer()
    }

    private fun initTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(8))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + (r * 0.15f)
        }
        binding.hadethViewPager.setPageTransformer(transformer)
    }

    private fun initVp2() {
        val adapter = HadethRecyclerViewAdapter()

        binding.hadethViewPager.offscreenPageLimit = 3
        binding.hadethViewPager.clipToPadding = false
        binding.hadethViewPager.clipChildren = false

        binding.hadethViewPager.adapter = adapter
    }
}