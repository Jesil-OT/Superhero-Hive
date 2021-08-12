package com.jesil.toborowei.hive.superherohive.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.databinding.FragmentSuperheroesBinding
import com.jesil.toborowei.hive.superherohive.model.viewmodel.SuperheroesViewModel
import com.jesil.toborowei.hive.superherohive.utils.DataResult
import com.jesil.toborowei.hive.superherohive.utils.adapter.SuperheroesAdapter
import com.jesil.toborowei.hive.superherohive.utils.hideShimmerUtils
import com.jesil.toborowei.hive.superherohive.utils.showLottieUtil
import com.jesil.toborowei.hive.superherohive.utils.showShimmerUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [SuperheroesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SuperheroesFragment : Fragment(R.layout.fragment_superheroes) {
    private val viewModel: SuperheroesViewModel by viewModels()
    private var _binding: FragmentSuperheroesBinding? = null
    private val binding get() = _binding!!
    private val superheroesAdapter: SuperheroesAdapter by lazy {
        SuperheroesAdapter(null)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSuperheroesBinding.bind(view)
        initSuperHeroData()
    }

    @SuppressLint("SetTextI18n")
    private fun initSuperHeroData() {
        viewModel.heroDataList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataResult.Success -> {
                    superheroesAdapter.submitList(result.data)
                    binding.apply {
                        superheroRecyclerView.apply {
                            showShimmerUtils()
                            superheroRecyclerView.adapter = superheroesAdapter
                        }
                        shimmerViewContainer.apply {
                            hideShimmerUtils()
                            stopShimmer()
                        }
                    }
                }
                is DataResult.Error -> {
                    binding.apply{
                        lottieAnimationViewNoInternet.showLottieUtil()
                        textViewError.text = "${result.error.message}."
                        shimmerViewContainer.apply {
                            hideShimmerUtils()
                            stopShimmer()
                        }
                    }
                }
                is DataResult.Loading -> {
                    binding.apply {
                        superheroRecyclerView.hideShimmerUtils()
                        shimmerViewContainer.apply {
                            showShimmerUtils()
                            startShimmer()
                        }

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}