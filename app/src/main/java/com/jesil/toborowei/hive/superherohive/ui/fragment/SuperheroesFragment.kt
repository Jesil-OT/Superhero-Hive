package com.jesil.toborowei.hive.superherohive.ui.fragment

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
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [SuperheroesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SuperheroesFragment : Fragment(R.layout.fragment_superheroes) {
    private val viewModel : SuperheroesViewModel by viewModels()
    private var _binding : FragmentSuperheroesBinding? = null
    private val binding get() = _binding!!
    private val superheroesAdapter: SuperheroesAdapter by lazy {
        SuperheroesAdapter(null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSuperheroesBinding.bind(view)
        Log.d("TAG", "onViewCreated: ")
        initSuperHeroData()
        binding.apply {

        }
    }

    private fun initSuperHeroData() {
        viewModel.heroDataList.observe(viewLifecycleOwner){ result ->
            when(result){
               is DataResult.Success -> {
                   Log.d("TAG", "initSuperHeroData: ${result.data}")
                   superheroesAdapter.submitList(result.data)
                   binding.superheroRecyclerView.adapter = superheroesAdapter
               }
                is DataResult.Error -> {
                    Log.d("TAG", "initSuperHeroData: ${result.error}")
                }
                is DataResult.Loading -> {
                    Log.d("TAG", "initSuperHeroData: Loading....")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}