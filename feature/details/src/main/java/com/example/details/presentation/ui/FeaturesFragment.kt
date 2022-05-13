package com.example.details.presentation.ui

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
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

            binding.apply {
                processorText.text = details.cPU
                ramText.text = details.sd
                romText.text = details.ssd
                cameraText.text = details.camera

                for (color in details.color) {
                    selectColorRg.addView(createRadioButton(color))
                    selectColorRg.check(selectColorRg.getChildAt(0).id)
                }
            }
        }
    }

    private fun createRadioButton(color: String): RadioButton {

        val radioButton = RadioButton(requireContext())
        val size = pxToDp(40)
        val margin = pxToDp(18)

        return radioButton.apply {

            val bg = AppCompatResources.getDrawable(binding.root.context, R.drawable.color_check_selector)
            bg?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                Color.parseColor(color),
                BlendModeCompat.SRC_ATOP
            )
            background = bg

            val params =
                RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, margin, 0)
            layoutParams = params

            width = size
            height = size
        }
    }

    private fun pxToDp(size: Int) =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            size.toFloat(),
            resources.displayMetrics
        ).toInt()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}