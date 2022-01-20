package com.example.ecommerce.presentation.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.*
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.presentation.ui.home.adapter.BestSellerAdapter
import com.example.ecommerce.presentation.ui.home.adapter.HotSalesAdapter
import com.example.ecommerce.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()

    private val bestSellerAdapter = BestSellerAdapter()
    private val hotSalesAdapter = HotSalesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.home.observe(viewLifecycleOwner) {
            bestSellerAdapter.setData(it.first().bestSeller)
            hotSalesAdapter.setData(it.first().homeStore)
        }

        binding.apply {

            hotSalesRecyclerView.adapter = hotSalesAdapter
            hotSalesRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(hotSalesRecyclerView)

            val gridLayoutManager = GridLayoutManager(requireContext(), 2)

            bestSellerRecyclerView.adapter = bestSellerAdapter
            bestSellerRecyclerView.layoutManager = gridLayoutManager
        }
    }
}