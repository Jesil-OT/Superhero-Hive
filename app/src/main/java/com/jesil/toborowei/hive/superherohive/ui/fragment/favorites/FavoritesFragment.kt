package com.jesil.toborowei.hive.superherohive.ui.fragment.favorites

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.data.local.PreferenceHelper
import com.jesil.toborowei.hive.superherohive.databinding.FragmentFavoritesBinding
import com.jesil.toborowei.hive.superherohive.model.HeroModel
import com.jesil.toborowei.hive.superherohive.ui.fragment.details.SuperheroDetailsActivity
import com.jesil.toborowei.hive.superherohive.utils.AppConstants.INTENT_KEY
import com.jesil.toborowei.hive.superherohive.utils.OnItemClickListener
import com.jesil.toborowei.hive.superherohive.utils.adapter.SuperheroesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites), OnItemClickListener {
    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private var _binding: FragmentFavoritesBinding? = null
    private var _toast: Toast? = null
    private val binding get() = _binding!!

    private val superheroesAdapter: SuperheroesAdapter by lazy {
        SuperheroesAdapter(preferenceHelper, this,
            { _ -> },
            { item -> //unlike
                item.isFavorite = false
                preferenceHelper.removeFavorite(item.id)
                favoritesViewModel.removeFavorites(item)
                _toast?.cancel()
                showToast("You Removed ${item.name} from your favorites")
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        binding.recyclerViewFavorites.adapter = superheroesAdapter
        favoritesViewModel.allFavorites.observe(viewLifecycleOwner) { favoritesResult ->
            setData(favoritesResult)
        }
    }

    private fun setData(data: List<HeroModel>) = with(binding) {
        superheroesAdapter.submitList(data)
        errorGroup.isVisible = data.isNullOrEmpty()
    }

    override fun onItemClick(superheroModel: HeroModel) {
        val action = Intent(requireContext(), SuperheroDetailsActivity::class.java).apply {
            putExtra(INTENT_KEY, superheroModel)
        }
        startActivity(action)
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