package com.segunfrancis.feature.home.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.segunfrancis.common.SuperheroesAdapter
import com.segunfrancis.feature.home.R
import com.segunfrancis.feature.home.databinding.FragmentSuperheroesBinding
import com.segunfrancis.feature.home.util.NetworkResult
import com.segunfrancis.feature.home.util.colorSchemeAndRefreshListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperheroesFragment : Fragment(R.layout.fragment_superheroes) {

    private val hiveViewModel: SuperheroesHiveViewModel by viewModels()
    private var _binding: FragmentSuperheroesBinding? = null
    private var _toast: Toast? = null
    private val binding get() = _binding!!

    private val superheroesAdapter: SuperheroesAdapter by lazy {
        SuperheroesAdapter({ itemClick ->
            // TODO: Navigate to details screen
            showToast(itemClick.isFavorite.toString())
        }, { itemLike ->
            // TODO: Add or remove from liked item depending on the value of `itemLike.isFavorite`
            showToast(itemLike.isFavorite.toString())
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSuperheroesBinding.bind(view)
        initSuperHeroData()
        binding.retry.apply {
            colorSchemeAndRefreshListener {
                hiveViewModel.getAllSuperheroes()
                hideErrorForNewData()
            }
        }
    }

    private fun initSuperHeroData() {
        hiveViewModel.heroesDataList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    superheroesAdapter.submitList(result.data)
                    successViews()
                }
                is NetworkResult.Error -> {
                    errorViews()
                }
                is NetworkResult.Loading -> {
                    loadingViews()
                }
            }
        }
    }

    private fun successViews() {
        binding.apply {
            superheroRecyclerView.apply {
                isVisible = true
                superheroRecyclerView.adapter = superheroesAdapter
            }
            customShimmerLayout.root.apply {
                isGone = true
                stopShimmer()
            }
            retry.isRefreshing = false
        }
    }

    private fun errorViews() {
        binding.apply {
            lottieAnimationViewNoInternet.isVisible = true
            textViewError.apply {
                isVisible = true
                text = resources.getString(R.string.network_error)
            }
            customShimmerLayout.root.apply {
                isGone = true
                stopShimmer()
            }
            retry.isRefreshing = false
        }
    }

    private fun loadingViews() {
        binding.apply {
            superheroRecyclerView.isGone = true
            customShimmerLayout.root.apply {
                isVisible = true
                startShimmer()
            }
            retry.isRefreshing = true
        }
    }

    private fun hideErrorForNewData() {
        binding.apply {
            lottieAnimationViewNoInternet.isGone = true
            textViewError.isGone = true
            retry.isRefreshing = false
        }
    }

    private fun showToast(message: String) {
        _toast = Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        )
        _toast?.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _toast?.cancel()
    }
}