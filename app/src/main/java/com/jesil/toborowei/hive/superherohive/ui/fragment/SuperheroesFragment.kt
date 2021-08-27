package com.jesil.toborowei.hive.superherohive.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSuperheroesBinding.bind(view)
        initSuperHeroData()
        binding.superheroRetry.setOnClickListener {
           // viewModel.loadSuperHeroResult()
        }

    }

    private fun initSuperHeroData() {
        viewModel.heroDataList.observe(viewLifecycleOwner) { result ->
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun successViews(){
        with(binding) {
            with(binding) {
                with(superheroRecyclerView) {
                    showUtils()
                    superheroRecyclerView.adapter = superheroesAdapter
                }
                with(customShimmerLayout.root) {
                    hideUtils()
                    stopShimmer()
                }
            }
        }
    }

    private fun errorViews(){
        with(binding) {
            lottieAnimationViewNoInternet.showUtils()
            textViewError.text = resources.getString(R.string.network_error)
            with(customShimmerLayout.root) {
                hideUtils()
                stopShimmer()
            }
            with(superheroRetry){
                showUtils()
            }
        }
    }

    private fun loadingViews(){
        with(binding) {
            superheroRecyclerView.hideUtils()
            with(customShimmerLayout.root) {
                showUtils()
                startShimmer()
            }
        }
    }
}