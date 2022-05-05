package com.example.homestore.presentation.ui.home

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.homestore.R
import com.example.homestore.databinding.FragmentHomeBinding
import com.example.homestore.presentation.ui.home.adapter.BestSellerAdapter
import com.example.homestore.presentation.ui.home.adapter.HotSalesAdapter
import com.example.homestore.presentation.ui.home.adapter.category.Category
import com.example.homestore.presentation.ui.home.adapter.category.CategoryAdapter
import com.example.homestore.presentation.viewmodel.MainViewModel
import com.example.navigation.NavigationFlow
import com.example.navigation.Navigator
import com.example.navigation.ToFlowNavigatable
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    private lateinit var bestSellerAdapter: BestSellerAdapter
    private lateinit var hotSalesAdapter: HotSalesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        bestSellerAdapter = BestSellerAdapter(requireActivity())
        hotSalesAdapter = HotSalesAdapter(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.home.observe(viewLifecycleOwner) {
            bestSellerAdapter.setData(it.bestSeller)
            hotSalesAdapter.setData(it.hotSales)
        }

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.mainToolbar)
        initRecyclerViews()
        initMoreTexts()

        val navBar: BottomNavigationView =
            requireActivity().findViewById(com.example.commonui.R.id.bottom_navigation_view)
        navBar.visibility = View.VISIBLE
    }



    override fun onResume() {
        super.onResume()
        initSearch()

        val locations =
            arrayOf("Zihuatanejo, Gro", "Moscow, Russia", "New York, USA", "Bambalabma, Hab", "Kurguda, Sato")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, locations)

        binding.apply {

            autoCompleteTextView.setAdapter(arrayAdapter)
            autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
                Toast.makeText(
                    requireContext(),
                    "Selected location is ${parent.getItemAtPosition(position)}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            mainFilterButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_homeFilterBottomSheet)
            }
        }
    }


    private fun initSearch() {
        binding.apply {

            val names = ArrayList<String>()

            mainViewModel.home.observe(viewLifecycleOwner) { resultItem ->
                resultItem.bestSeller.forEach {
                    names.add(it.title)
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
                } else
                    Toast.makeText(requireContext(), "Searching by QR-Code", Toast.LENGTH_SHORT).show()
            }

            mainFilterButton.setOnClickListener {

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
                (requireActivity() as ToFlowNavigatable).navigateToFlow(NavigationFlow.DetailsFlow)
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