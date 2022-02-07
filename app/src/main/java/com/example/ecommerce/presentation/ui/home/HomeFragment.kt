package com.example.ecommerce.presentation.ui.home

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat.getSystemService
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
            hotSalesAdapter.setData(it.first().hotSales)
        }

        initRecyclerViews()
        initMoreTexts()
    }

    override fun onResume() {
        super.onResume()
        initSearch()
    }

    private fun initSearch() {
        binding.apply {

            val names = ArrayList<String>()

            mainViewModel.home.observe(viewLifecycleOwner) { list ->
                list.map { resultItem ->
                    resultItem.bestSeller.forEach {
                        names.add(it.title)
                    }
                }
            }

            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, names)

            homeSearchField.setAdapter(arrayAdapter)
            homeSearchField.setOnItemClickListener { parent, view, position, id ->
                Toast.makeText(
                    requireContext(),
                    "Filter by ${parent.getItemAtPosition(position)}",
                    Toast.LENGTH_SHORT
                ).show()
                searchByTitle(parent.getItemAtPosition(position).toString())
            }

            homeSearchField.setOnKeyListener { _, keyCode, keyEvent ->
                if ((keyEvent.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    searchByTitle(homeSearchField.text.toString())
                    homeSearchByQrButton.hideKeyboard()
                    homeSearchField.text.clear()
                }
                false
            }

            homeSearchByQrButton.setOnClickListener {
                val searchQuery = homeSearchField.text.toString()

                if (searchQuery.isNotBlank()) {
                    searchByTitle(searchQuery)
                    homeSearchByQrButton.hideKeyboard()
                    homeSearchField.text.clear()
                }
                else
                    Toast.makeText(requireContext(), "Searching by QR-Code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun searchByTitle(search: String): ArrayList<String> {
        val titles = ArrayList<String>()
        val searchQuery = "%$search%"

        mainViewModel.searchBestSellerByName(searchQuery).observe(viewLifecycleOwner) {
            it.let { list ->
                bestSellerAdapter.setData(list)
                list.forEach { seller ->
                    titles.add(seller.title)
                }
            }
        }

        return titles
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

    // Эмитация добавления категорий через лайвдату
    private fun createCategories(): ArrayList<Category> {
        return arrayListOf(
            Category(AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_phone), "Phones"),
            Category(AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_computer), "Computer"),
            Category(AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_health), "Health"),
            Category(AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_books), "Books"),
            Category(AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_phone), "Phones"),
            Category(AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_computer), "Computer"),
            Category(AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_health), "Health"),
            Category(AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_books), "Books"),
        )
    }
}