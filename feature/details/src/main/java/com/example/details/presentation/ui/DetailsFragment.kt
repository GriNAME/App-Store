package com.example.details.presentation.ui

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.details.R
import com.example.details.databinding.FragmentDetailsBinding
import com.example.details.presentation.ui.adapter.FeaturesPagerAdapter
import com.example.details.presentation.ui.adapter.PicturesPagerAdapter
import com.example.details.presentation.viewmodel.DetailsViewModel
import com.example.details_api.domain.model.CartItem
import com.example.details_api.domain.model.Product
import com.example.navigation.NavigationFlow
import com.example.navigation.ToFlowNavigatable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.math.abs


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initFeaturesPagerAdapter()
        initPicturesPagerAdapter()

        val navBar: BottomNavigationView =
            requireActivity().findViewById(com.example.commonui.R.id.bottom_navigation_view)
        navBar.visibility = View.GONE
    }


    private fun initToolbar() {

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.apply {

            cartButton.setOnClickListener {
                (requireActivity() as ToFlowNavigatable).navigateToFlow(NavigationFlow.CartFlow)
            }

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            detailsViewModel.cartItems.observe(viewLifecycleOwner) {
                goodsCounterText.text = it.size.toString()
            }
        }
    }

    private fun initPicturesPagerAdapter() {
        detailsViewModel.details.observe(viewLifecycleOwner) { details ->
            val picturesPagerAdapter = PicturesPagerAdapter(details.images)

            val marginPageTransformer = MarginPageTransformer(150)

            binding.viewpagerPictures.apply {
                adapter = picturesPagerAdapter
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

                val composPageTran = CompositePageTransformer()
                composPageTran.addTransformer(MarginPageTransformer(0))
                composPageTran.addTransformer { page, position ->
                    val r: Float = 1 - abs(position)
                    page.scaleY = 0.6f + r * 0.2f
                }

                setPageTransformer(composPageTran)
            }

            binding.apply {
                titleDetails.text = details.title
                ratingDetails.rating = details.rating.toFloat()

                if (details.isFavorites) addToFavoriteButton.setImageResource(R.drawable.ic_favorite)
                addToFavoriteButton.setOnClickListener {
                    when (details.isFavorites) {
                        true -> addToFavoriteButton.setImageResource(R.drawable.ic_favorite_outline)
                        false -> addToFavoriteButton.setImageResource(R.drawable.ic_favorite)
                    }
                }

                val dec = DecimalFormat("#,###.00", DecimalFormatSymbols(Locale.ENGLISH))
                val str: String = dec.format(details.price.toDouble())

                addToCartButton.apply {
                    text = "Add to cart      $$str"
                    setOnClickListener {
                        val product = Product(
                            0,
                            details.images,
                            details.price,
                            details.title
                        )
                        val cartItem = CartItem(
                            0,
                            product,
                            1
                        )
                        detailsViewModel.insertItemToCart(cartItem)
                    }
                }
            }
        }
    }

    private fun initFeaturesPagerAdapter() {
        val pagerAdapter = FeaturesPagerAdapter(requireActivity())

        binding.viewpagerDetails.adapter = pagerAdapter

        TabLayoutMediator(binding.tabsDetails, binding.viewpagerDetails) { tab, position ->
            val tabNames = listOf("Shop", "Details", "Features")
            tab.text = tabNames[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}