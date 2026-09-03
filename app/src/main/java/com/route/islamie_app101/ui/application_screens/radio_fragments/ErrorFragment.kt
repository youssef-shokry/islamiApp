package com.route.islamie_app101.ui.application_screens.radio_fragments

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.route.islamie_app101.databinding.FragmentErrorBinding
import com.route.islamie_app101.utils.Constants.Companion.RADIO_RETRY
import com.route.islamie_app101.utils.Constants.Companion.TAB_NUM

class ErrorFragment : DialogFragment() {

    private lateinit var binding: FragmentErrorBinding
    private val args by navArgs<ErrorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.errorText.text = args.ErrorMessage
        binding.retryButton.setOnClickListener {
            dismiss()
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        parentFragmentManager.setFragmentResult(RADIO_RETRY, Bundle().apply {
            putInt(TAB_NUM, args.TabNum)
        })
    }
}