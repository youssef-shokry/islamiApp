package com.route.islamie_app101.ui.application_screens.hadeth_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.route.islamie_app101.databinding.FragmentSelectHadethBinding
import com.route.islamie_app101.domain.data_models.hadeth.HadethDataModel
import com.route.islamie_app101.ui.IslamiViewModel
import com.route.islamie_app101.ui.application_screens.hadeth_fragments.hadeth_view_pager.HadethAdapter
import com.route.islamie_app101.ui.application_screens.hadeth_fragments.hadeth_view_pager.SetOnHadethClick
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class SelectHadethFragment : Fragment() {

    private lateinit var binding: FragmentSelectHadethBinding
    private lateinit var adapter: HadethAdapter
    private val islamiViewModel: IslamiViewModel by viewModels()
    private var ahadethList: List<HadethDataModel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectHadethBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initHadethContent()
        initVp2()
        initTransformer()
        initListeners()
    }

    private fun initListeners() {
        adapter.setOnHadethClick = object : SetOnHadethClick {
            override fun onHadethClick(hadeth: HadethDataModel) {
                val action =
                    SelectHadethFragmentDirections.actionSelectHadethFragmentToHadethFragment(hadeth)
                findNavController().navigate(action)
            }
        }
    }

    private fun initHadethContent() {
        ahadethList = islamiViewModel.ahadethList
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
        adapter = HadethAdapter(ahadethList)

        binding.hadethViewPager.offscreenPageLimit = 3
        binding.hadethViewPager.clipToPadding = false
        binding.hadethViewPager.clipChildren = false

        binding.hadethViewPager.adapter = adapter
    }
}