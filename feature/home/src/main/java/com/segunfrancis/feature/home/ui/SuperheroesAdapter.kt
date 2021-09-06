package com.segunfrancis.feature.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.like.LikeButton
import com.like.OnLikeListener
import com.segunfrancis.feature.home.R
import com.segunfrancis.feature.home.databinding.ItemEachHeroBinding
import com.segunfrancis.feature.home.model.HeroModel
import com.segunfrancis.feature.home.util.OnItemClickListener

class SuperheroesAdapter(
    private val preferenceHelper: PreferenceHelper,
    private val _listener: OnItemClickListener,
    private val onItemLike: (item: HeroModel) -> Unit,
    private val onItemUnlike: (item: HeroModel) -> Unit
) :
    ListAdapter<HeroModel, SuperheroesAdapter.SuperheroesViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroesViewHolder {
        val binding =
            ItemEachHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperheroesViewHolder(binding)
    }

    override fun onBindViewHolder(holderSuperheroes: SuperheroesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holderSuperheroes.bind(currentItem, onItemLike, onItemUnlike)
        }
    }

    inner class SuperheroesViewHolder(private val binding: ItemEachHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.root.setOnClickListener {
                val item = getItem(adapterPosition)
                if (item != null) {
                    _listener.onItemClick(item)
                }
            }
        }

        private val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_on_loading_image)
            .error(R.drawable.ic_broken_image)

        fun bind(
            heroModel: HeroModel,
            onItemLike: (item: HeroModel) -> Unit,
            onItemUnlike: (item: HeroModel) -> Unit
        ) {
            with(binding) {
                Glide.with(itemView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(heroModel.images.mediumImage)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageSrcMedium)

                when {
                    heroModel.biography.publisher.equals(MARVEL, true) -> {
                        Glide.with(itemView)
                            .load(R.drawable.ic_marvel)
                            .into(imageRace)
                    }
                    heroModel.biography.publisher.equals(DC, true) -> {
                        Glide.with(itemView)
                            .load(R.drawable.ic_dc)
                            .into(imageRace)
                    }
                    heroModel.biography.publisher.equals(IDW, true) -> {
                        Glide.with(itemView)
                            .load(R.drawable.ic_idw)
                            .into(imageRace)
                    }
                    else -> {
                        imageRace.setImageResource(R.drawable.ic_unknown_publisher)
                    }
                }
                textViewName.text = heroModel.name
                addToFavorites.setOnLikeListener(object : OnLikeListener {
                    override fun liked(likeButton: LikeButton?) {
                        onItemLike(heroModel)
                    }

                    override fun unLiked(likeButton: LikeButton?) {
                        onItemUnlike(heroModel)
                    }
                })
                addToFavorites.isLiked = preferenceHelper.getFavorite(heroModel.id)
            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<HeroModel>() {
        override fun areItemsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
            return oldItem == newItem
        }
    }
}