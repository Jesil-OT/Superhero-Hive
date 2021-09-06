package com.segunfrancis.feature.home.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.segunfrancis.feature.home.model.HeroModel
import com.segunfrancis.feature.home.R
import com.segunfrancis.feature.home.databinding.FragmentSuperheroesBinding
import com.segunfrancis.feature.home.util.OnItemClickListener
import com.segunfrancis.feature.home.util.colorSchemeAndRefreshListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SuperheroesFragment : Fragment(R.layout.fragment_superheroes), OnItemClickListener {

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private val hiveViewModel: SuperherosHiveViewModel by viewModels()
    private var _binding: FragmentSuperheroesBinding? = null
    private var _toast: Toast? = null
    private val binding get() = _binding!!

    private val superheroesAdapter: SuperheroesAdapter by lazy {
        SuperheroesAdapter(preferenceHelper, this,
            { item -> //liked
                item.isFavorite = true
                preferenceHelper.addFavorite(item.id)
                hiveViewModel.setFavorites(item)
                showToast("added ${item.name} to favorites")

            },
            { item -> //unlike
                item.isFavorite = false
                preferenceHelper.removeFavorite(item.id)
                hiveViewModel.removeFavorites(item)
                _toast?.cancel()
                showToast("removed ${item.name} from favorites")
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSuperheroesBinding.bind(view)
        initSuperHeroData()
        binding.retry.apply {
            colorSchemeAndRefreshListener {
                hiveViewModel.loadSuperHeroResult()
                hideErrorForNewData()
            }
        }
    }

    private fun initSuperHeroData() {
        hiveViewModel.heroDataList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataResult.Success -> {
                    superheroesAdapter.submitList(result.data)
                    successViews()
                }
                is DataResult.Error -> {
                    errorViews()
                }
                is DataResult.Loading -> {
                    loadingViews()
                }
            }
        }
    }

    override fun onItemClick(superheroModel: HeroModel) {

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