package com.example.ecommerce.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ecommerce.R
import com.example.ecommerce.databinding.BottomSheetHomeFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFilterBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetHomeFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = BottomSheetHomeFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            filterDoneButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFilterBottomSheet_to_homeFragment2)
            }

            filterCloseButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        initFilterCategories()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

    private fun initFilterCategories() {
        val brands =
            arrayOf("Samsung", "Motorola", "Xiaomi")
        val arrayBrands = ArrayAdapter(requireContext(), R.layout.dropdown_item, brands)

        binding.apply {

            filterBrandAutocomplete.setAdapter(arrayBrands)
            filterBrandAutocomplete.setOnItemClickListener { parent, view, position, id ->
                Toast.makeText(
                    requireContext(),
                    "Filter by brand ${parent.getItemAtPosition(position)}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val prices =
            arrayOf("100\$ - 300\$", "300\$ - 500\$", "500\$ - 700\$")
        val arrayPrice = ArrayAdapter(requireContext(), R.layout.dropdown_item, prices)

        binding.apply {

            filterPriceAutocomplete.setAdapter(arrayPrice)
            filterPriceAutocomplete.setOnItemClickListener { parent, view, position, id ->
                Toast.makeText(
                    requireContext(),
                    "Filter by brand ${parent.getItemAtPosition(position)}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val sizes =
            arrayOf("3.5 to 4.5 inches", "4.5 to 5.5 inches", "5.5 to 6.5 inches")
        val arraySize = ArrayAdapter(requireContext(), R.layout.dropdown_item, sizes)

        binding.apply {

            filterSizeAutocomplete.setAdapter(arraySize)
            filterSizeAutocomplete.setOnItemClickListener { parent, view, position, id ->
                Toast.makeText(
                    requireContext(),
                    "Filter by brand ${parent.getItemAtPosition(position)}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}