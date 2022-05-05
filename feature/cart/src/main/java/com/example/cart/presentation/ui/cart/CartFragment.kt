package com.example.cart.presentation.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cart.databinding.FragmentCartBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navBar: BottomNavigationView = requireActivity().findViewById(com.example.commonui.R.id.bottom_navigation_view)
        navBar.visibility = View.GONE

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}