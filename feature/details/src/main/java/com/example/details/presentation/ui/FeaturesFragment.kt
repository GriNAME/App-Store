package com.example.details.presentation.ui

import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.details.R
import com.example.details.databinding.PagerDetailsBinding
import com.example.details.presentation.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeaturesFragment : Fragment() {

    private var _binding: PagerDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = PagerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsViewModel.details.observe(viewLifecycleOwner) { details ->

//            val radioButton = RadioButton(requireContext())
//            radioButton.apply {
//                setBackgroundColor(binding.root.context.getColor(R.color.accent))
//                width = 140
//                height = 140
////                setButtonDrawable(R.drawable.color_check_selector)
//                buttonDrawable = AppCompatResources.getDrawable(binding.root.context, R.drawable.color_check_selector)
//            }

//            val attrs: AttributeSet = AttributeSet()

            binding.apply {
                processorText.text = details.cPU
                ramText.text = details.sd
                romText.text = details.ssd
                cameraText.text = details.camera
//                selectColorRg.addView(radioButton)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}