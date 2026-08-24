package com.route.islamie_app101.ui.application_screens.radio_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.route.islamie_app101.R
import com.route.islamie_app101.databinding.FragmentRadioBinding
import com.route.islamie_app101.domain.data_models.radio.RadioDataModel
import com.route.islamie_app101.domain.data_models.radio.ReciterDataModel
import com.route.islamie_app101.ui.IslamiViewModel
import com.route.islamie_app101.ui.application_screens.radio_fragments.radio_adapter.RadioItemAdapter
import com.route.islamie_app101.ui.application_screens.radio_fragments.radio_adapter.RadioPagerAdapter
import com.route.islamie_app101.ui.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioFragment : Fragment() {
    private lateinit var binding: FragmentRadioBinding
    private lateinit var viewPagerAdapter: RadioPagerAdapter
    private lateinit var radioAdapter: RadioItemAdapter<RadioDataModel>
    private lateinit var reciterAdapter: RadioItemAdapter<ReciterDataModel>
    private var radioList: List<RadioDataModel> = emptyList()
    private var reciterList: List<ReciterDataModel> = emptyList()
    private val viewModel: IslamiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewsAdapter()
        setupViewPagerAdapter()
        loadRadioList()
        loadRecitersList()
    }

    fun setupViewPagerAdapter() {
        viewPagerAdapter = RadioPagerAdapter(radioAdapter, reciterAdapter)
        binding.radioViewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.radioViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.radioR)
                1 -> getString(R.string.reciters)
                else -> ""
            }
        }.attach()

        binding.radioViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> viewModel.loadRadioTab()
                        1 -> viewModel.loadRecitersTab()
                    }
                }
            }
        )

    }

    fun setupRecyclerViewsAdapter() {
        radioAdapter = RadioItemAdapter(radioList) { radioItem, listItem ->
            radioItem.radioNameText.text = listItem.name
        }

        reciterAdapter = RadioItemAdapter(reciterList) { radioItem, listItem ->
            radioItem.radioNameText.text = listItem.name
        }
    }

    fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    fun loadRadioList() {
        viewModel.radioState.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    radioAdapter.submitList(resource.data)
                }
                is Resource.Error -> {//Todo}
                }
            }
        }
    }

    fun loadRecitersList() {
        viewModel.recitersState.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    reciterAdapter
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    reciterAdapter.submitList(resource.data)
                }

                is Resource.Error -> {//Todo}
                }
            }
        }
    }
}