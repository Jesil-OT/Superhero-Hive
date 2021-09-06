package com.jesil.toborowei.hive.superherohive.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.data.local.PreferenceHelper
import com.jesil.toborowei.hive.superherohive.databinding.FragmentSuperheroesBinding
import com.jesil.toborowei.hive.superherohive.model.HeroModel
import com.jesil.toborowei.hive.superherohive.ui.SuperheroesHiveViewModel
import com.jesil.toborowei.hive.superherohive.ui.details.SuperheroDetailsActivity
import com.jesil.toborowei.hive.superherohive.utils.*
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.INTENT_KEY
import com.jesil.toborowei.hive.superherohive.utils.adapter.SuperheroesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [SuperheroesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class SuperheroesFragment : Fragment(R.layout.fragment_superheroes), OnItemClickListener {

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private val hiveViewModel: SuperheroesHiveViewModel by viewModels()
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
        val action = Intent(requireContext(), SuperheroDetailsActivity::class.java).apply {
            putExtra(INTENT_KEY, superheroModel)
        }
        startActivity(action)
    }

    private fun successViews() {
        binding.apply {
            superheroRecyclerView.apply {
                showUtils()
                superheroRecyclerView.adapter = superheroesAdapter
            }
            customShimmerLayout.root.apply {
                hideUtils()
                stopShimmer()
            }
            retry.isRefreshing = false
        }
    }

    private fun errorViews() {
        binding.apply {
            lottieAnimationViewNoInternet.showUtils()
            textViewError.apply {
                showUtils()
                text = resources.getString(R.string.network_error)
            }
            customShimmerLayout.root.apply {
                hideUtils()
                stopShimmer()
            }
            retry.isRefreshing = false
        }
    }

    private fun loadingViews() {
        binding.apply {
            superheroRecyclerView.hideUtils()
            customShimmerLayout.root.apply {
                showUtils()
                startShimmer()
            }
            retry.isRefreshing = true
        }
    }

    private fun hideErrorForNewData() {
        binding.apply {
            lottieAnimationViewNoInternet.hideUtils()
            textViewError.hideUtils()
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