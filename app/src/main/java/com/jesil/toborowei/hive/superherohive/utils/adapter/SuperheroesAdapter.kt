package com.jesil.toborowei.hive.superherohive.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jesil.toborowei.hive.superherohive.R
import com.jesil.toborowei.hive.superherohive.databinding.ItemEachHeroBinding
import com.jesil.toborowei.hive.superherohive.model.HeroModel
import com.jesil.toborowei.hive.superherohive.utils.OnItemClickListener

class SuperheroesAdapter(private val _listener: OnItemClickListener?) :
    ListAdapter<HeroModel, SuperheroesAdapter.SuperheroesViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroesViewHolder {
        val binding = ItemEachHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperheroesViewHolder(binding)
    }

    override fun onBindViewHolder(holderSuperheroes: SuperheroesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null){
            holderSuperheroes.bind(currentItem)
        }
    }

    inner class SuperheroesViewHolder(private val binding: ItemEachHeroBinding) :
        RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                    val item = getItem(position)
                    if (item != null){
                        _listener?.onItemClick(item)
                }
            }
        }
        private val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        fun bind(heroModel: HeroModel) {
            binding.apply {
                Glide.with(itemView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(heroModel.images.md)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageSrcMedium)
                when(heroModel.biography.publisher){
                    "Marvel Comics" -> {
                        Glide.with(itemView)
                        .load(R.drawable.ic_marvel)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageRace)
                    }
                    "DC Comics" -> {
                        Glide.with(itemView)
                            .load(R.drawable.ic_dc)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(imageRace)
                    }
                }
                textViewName.text = heroModel.name
            }
        }
    }


    class UserComparator : DiffUtil.ItemCallback<HeroModel>(){
        override fun areItemsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
            return oldItem == newItem
        }
    }
}