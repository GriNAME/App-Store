package com.example.ecommerce.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.*
import com.example.ecommerce.R
import com.example.ecommerce.databinding.FragmentHomeBinding
import com.example.ecommerce.presentation.ui.home.adapter.BestSellerAdapter
import com.example.ecommerce.presentation.ui.home.adapter.HotSalesAdapter
import com.example.ecommerce.presentation.ui.home.adapter.category.Category
import com.example.ecommerce.presentation.ui.home.adapter.category.CategoryAdapter
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

        initRecyclerViews()
        initMoreTexts()
    }

    private fun initMoreTexts() {

        binding.apply {

            homeCategoriesMoreText.setOnClickListener {
                Toast.makeText(requireContext(), "Show all categories", Toast.LENGTH_SHORT).show()
            }

            homeHotSalesMoreText.setOnClickListener {
                Toast.makeText(requireContext(), "Navigate to Hot Sales list", Toast.LENGTH_SHORT).show()
            }

            homeBestSellerMoreText.setOnClickListener {
                Toast.makeText(requireContext(), "Navigate to Best Seller list", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerViews() {
        binding.apply {

            homeHotSalesRecyclerView.adapter = hotSalesAdapter
            homeHotSalesRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(homeHotSalesRecyclerView)

            val gridLayoutManager = GridLayoutManager(requireContext(), 2)

            homeBestSellerRecyclerView.adapter = bestSellerAdapter
            homeBestSellerRecyclerView.layoutManager = gridLayoutManager

            val categoryAdapter = CategoryAdapter(createCategories())
            homeCategoriesRecyclerView.adapter = categoryAdapter
            homeCategoriesRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun createCategories(): ArrayList<Category> {
        return arrayListOf<Category>(
            Category(requireContext().getDrawable(R.drawable.ic_phone), "Phones", true),
            Category(requireContext().getDrawable(R.drawable.ic_computer), "Computer"),
            Category(requireContext().getDrawable(R.drawable.ic_health), "Health"),
            Category(requireContext().getDrawable(R.drawable.ic_books), "Books"),
            Category(requireContext().getDrawable(R.drawable.ic_phone), "Phones"),
            Category(requireContext().getDrawable(R.drawable.ic_computer), "Computer"),
            Category(requireContext().getDrawable(R.drawable.ic_health), "Health"),
            Category(requireContext().getDrawable(R.drawable.ic_books), "Books"),
        )
    }
}