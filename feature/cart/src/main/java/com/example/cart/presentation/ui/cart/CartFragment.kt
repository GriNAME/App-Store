package com.example.cart.presentation.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cart.databinding.FragmentCartBinding
import com.example.cart.presentation.ui.cart.adapter.CartAdapter
import com.example.cart.presentation.viewmodel.CartViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val cartViewModel by viewModels<CartViewModel>()

    private val cartAdapter by lazy { CartAdapter(requireActivity(), cartViewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navBar: BottomNavigationView =
            requireActivity().findViewById(com.example.commonui.R.id.bottom_navigation_view)
        navBar.visibility = View.GONE

        initToolbar()
        initCartRecyclerView()

        cartViewModel.cartItems.observe(viewLifecycleOwner) { list ->

            val resultList = ArrayList<Int>()

            list.map {
                resultList.add(it.product.price * it.quantity)
            }

            binding.totalPriceText.text = resultList.sum().toString()
        }
    }

    private fun initCartRecyclerView() {
        cartViewModel.cartItems.observe(viewLifecycleOwner) {
            cartAdapter.setData(it)

            binding.cartRecyclerView.apply {
                adapter = cartAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun initToolbar() {

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}