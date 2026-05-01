package com.route.islamie_app101.ui.application_screens.sebha_fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.islamie_app101.R
import com.route.islamie_app101.databinding.FragmentSebhaBinding
import kotlinx.coroutines.Runnable


class SebhaFragment : Fragment() {

    private lateinit var binding: FragmentSebhaBinding
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var numberOfTasbeh: Int = 0
    private val listOfTasbehText: List<String> =
        mutableListOf("سبحان الله", "الحمد لله", "الله اكبر") //Todo make it combatable with clean code

    private var listIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSebhaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSebha()
        sebhaFunctionality()
    }

    private fun initSebha() {
        binding.tasbehText.text = listOfTasbehText[listIndex]
        binding.tasbehNumbers.text = numberOfTasbeh.toString()
        binding.sebhaBody.setImageResource(R.drawable.sebha_body)
    }

    private fun sebhaFunctionality() {
        binding.sebhaBody.setOnClickListener {

            binding.sebhaBody.setImageResource(R.drawable.sebha_effect)


            handler = Handler(Looper.getMainLooper())
            runnable = Runnable {
                binding.sebhaBody.animate().rotationBy(30f)
                binding.sebhaBody.setImageResource(R.drawable.sebha_body)
            }

            numberOfTasbeh++

            sebhaTextChange()

            binding.tasbehNumbers.text = numberOfTasbeh.toString()

            handler.postDelayed(runnable, 200)
        }
    }

    private fun sebhaTextChange() {
        if (numberOfTasbeh >= 34) {

            numberOfTasbeh = 0

            listIndex = (listIndex + 1) % listOfTasbehText.size

            binding.tasbehText.text = listOfTasbehText[listIndex]
        }
    }
}