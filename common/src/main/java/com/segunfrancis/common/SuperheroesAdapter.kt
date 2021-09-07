package com.segunfrancis.common

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
import com.segunfrancis.common.databinding.ItemEachHeroBinding
import com.segunfrancis.common.model.HeroModel
import com.segunfrancis.common.util.CommonConstants.DC
import com.segunfrancis.common.util.CommonConstants.IDW
import com.segunfrancis.common.util.CommonConstants.MARVEL

class SuperheroesAdapter(
    private val onItemClick: (HeroModel) -> Unit,
    private val onItemLike: (HeroModel) -> Unit
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
            holderSuperheroes.bind(currentItem, onItemLike)
        }
    }

    inner class SuperheroesViewHolder(private val binding: ItemEachHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.root.setOnClickListener {
                val item = getItem(adapterPosition)
                if (item != null) {
                    onItemClick(item)
                }
            }
        }

        private val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_on_loading_image)
            .error(R.drawable.ic_broken_image)

        fun bind(
            heroModel: HeroModel,
            onItemLike: (item: HeroModel) -> Unit
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
                        onItemLike(heroModel.copy(isFavorite = true))
                    }

                    override fun unLiked(likeButton: LikeButton?) {
                        onItemLike(heroModel.copy(isFavorite = false))
                    }
                })
                addToFavorites.isLiked = heroModel.isFavorite
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
