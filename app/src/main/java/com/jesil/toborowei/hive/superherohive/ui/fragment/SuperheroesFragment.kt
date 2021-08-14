package com.jesil.toborowei.hive.superherohive.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.databinding.FragmentSuperheroesBinding
import com.jesil.toborowei.hive.superherohive.model.HeroModel
import com.jesil.toborowei.hive.superherohive.model.viewmodel.SuperheroesViewModel
import com.jesil.toborowei.hive.superherohive.ui.fragment.details.SuperheroDetailsActivity
import com.jesil.toborowei.hive.superherohive.utils.*
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.INTENT_KEY
import com.jesil.toborowei.hive.superherohive.utils.adapter.SuperheroesAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [SuperheroesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SuperheroesFragment : Fragment(R.layout.fragment_superheroes), OnItemClickListener {
    private val viewModel: SuperheroesViewModel by viewModels()
    private var _binding: FragmentSuperheroesBinding? = null
    private val binding get() = _binding!!
    private val superheroesAdapter: SuperheroesAdapter by lazy {
        SuperheroesAdapter(this)
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
                        customShimmerLayout.root.apply {
                            hideShimmerUtils()
                            stopShimmer()
                        }
                    }
                }
                is DataResult.Error -> {
                    binding.apply {
                        lottieAnimationViewNoInternet.showLottieUtil()
                        textViewError.text = "${result.error.message}."
                        customShimmerLayout.root.apply {
                            hideShimmerUtils()
                            stopShimmer()
                        }
                    }
                }
                is DataResult.Loading -> {
                    binding.apply {
                        superheroRecyclerView.hideShimmerUtils()
                        customShimmerLayout.root.apply {
                            showShimmerUtils()
                            startShimmer()
                        }

                    }
                }
            }
        }
    }

    override fun onItemClick(superheroModel: HeroModel) {
        val action = Intent(requireContext(), SuperheroDetailsActivity::class.java).apply{
            putExtra(INTENT_KEY, superheroModel)
        }
        startActivity(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}