package com.route.islamie_app101.ui.application_screens.radio_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
import com.route.islamie_app101.utils.Constants.Companion.RADIO_RETRY
import com.route.islamie_app101.utils.Constants.Companion.TAB_NUM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioFragment : Fragment() {
    private lateinit var binding: FragmentRadioBinding
    private lateinit var viewPagerAdapter: RadioPagerAdapter
    private lateinit var radioAdapter: RadioItemAdapter<RadioDataModel>
    private lateinit var reciterAdapter: RadioItemAdapter<ReciterDataModel>
    private var radioList: List<RadioDataModel?> = emptyList()
    private var reciterList: List<ReciterDataModel?> = emptyList()
    private var isRadioListTheSame: Boolean = false
    private var isRecitersListTheSame: Boolean = false
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
        linkTabsWithViewPager()
        loadDataPerTab()
        radioListState()
        recitersListState()
        observeRetry()
    }

    fun setupViewPagerAdapter() {
        viewPagerAdapter = RadioPagerAdapter(radioAdapter, reciterAdapter)
        binding.radioViewPager.adapter = viewPagerAdapter
    }

    fun loadDataPerTab() {
        binding.radioViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    loadSelectedTab(position)
                }
            }
        )
    }

    private fun loadSelectedTab(position: Int) {
        when (position) {
            0 -> {
                if (radioList.all { it == null }
                        .or(radioList.isEmpty())) viewModel.loadRadioList()
            }

            1 -> {
                if (reciterList.all { it == null }
                        .or(reciterList.isEmpty())) viewModel.loadRecitersList()
            }
        }
    }

    fun linkTabsWithViewPager() {
        TabLayoutMediator(binding.tabLayout, binding.radioViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.radioR)
                1 -> getString(R.string.reciters)
                else -> ""
            }
        }.attach()
    }


    fun setupRecyclerViewsAdapter() {
        radioAdapter = RadioItemAdapter { radioItem, listItem ->
            radioItem.radioNameText.text = listItem?.name

            radioItem.playButton.setOnClickListener {
                //TODO
            }
        }

        reciterAdapter = RadioItemAdapter { radioItem, listItem ->
            radioItem.radioNameText.text = listItem?.name

            radioItem.playButton.setOnClickListener {
                //TODO
            }
        }
    }

    fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    fun radioListState() {
        viewModel.radioState.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    if (radioList.contains(null).or(radioList.isEmpty())) showLoading()
                }

                is Resource.Success -> {
                    if (radioList.isEmpty()) {
                        radioList = resource.data
                        radioAdapter.submitList(radioList)
                        hideLoading()
                    } else {
                        if (radioList == resource.data) {
                            return@observe
                        }
                        radioList = resource.data
                        radioAdapter.submitList(radioList)
                        hideLoading()
                    }
                }

                is Resource.Error -> {
                    showErrorFragment(resource.errorMessage, 0)
                }

            }
        }
    }

    fun recitersListState() {
        viewModel.recitersState.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    if (reciterList.contains(null).or(reciterList.isEmpty())) showLoading()
                }

                is Resource.Success -> {
                    if (reciterList.isEmpty()) {
                        reciterList = resource.data
                        reciterAdapter.submitList(reciterList)
                        hideLoading()
                    } else {
                        if (reciterList == resource.data) {
                            return@observe
                        }
                        reciterList = resource.data
                        reciterAdapter.submitList(reciterList)
                        isRecitersListTheSame = false
                        hideLoading()
                    }
                }

                is Resource.Error -> {
                    showErrorFragment(resource.errorMessage, 1)
                }
            }
        }
    }

    private fun showErrorFragment(
        message: String,
        tabNum: Int
    ) {
        if (findNavController().currentDestination?.id != R.id.radioFragment) {
            return
        }

        val action =
            RadioFragmentDirections.actionRadioFragmentToErrorFragment(
                message,
                tabNum
            )
        findNavController().navigate(action)
    }

    fun observeRetry() {
        parentFragmentManager.setFragmentResultListener(
            RADIO_RETRY,
            viewLifecycleOwner
        ) { _, bundle ->
            val tabNum = bundle.getInt(TAB_NUM)
            binding.root.post {
                if (findNavController().currentDestination?.id != R.id.radioFragment) {
                    binding.root.post {
                        retryData(tabNum)
                    }
                    return@post
                }
                retryData(tabNum)
            }
        }
    }


    private fun retryData(tabNum: Int) {
        when (tabNum) {
            0 -> {
                isRadioListTheSame = false

                viewModel.loadRadioList()
            }

            1 -> {
                isRecitersListTheSame = false
                viewModel.loadRecitersList()
            }
        }
    }
}