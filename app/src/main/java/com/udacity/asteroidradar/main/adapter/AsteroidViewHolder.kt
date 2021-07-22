package com.udacity.asteroidradar.main.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidItemBinding

class AsteroidViewHolder private constructor(val binding: AsteroidItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val res: Resources = itemView.context.resources

    fun bind(item: Asteroid, clickCallback: MainAdapter.ClickCallback) {
        binding.asteroid = item
        binding.clickCallback = clickCallback
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binder = AsteroidItemBinding.inflate(layoutInflater, parent, false)
            return AsteroidViewHolder(binder)
        }
    }
}
