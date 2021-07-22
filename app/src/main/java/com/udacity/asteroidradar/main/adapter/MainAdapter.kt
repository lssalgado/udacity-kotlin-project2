package com.udacity.asteroidradar.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainAdapter(val clickCallback: ClickCallback) :
    ListAdapter<Asteroid, RecyclerView.ViewHolder>(AsteroidDiffCallback()) {
    class ClickCallback(val clickListener: (asteroidId: Long) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid.id)
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun submitList(list: List<Asteroid>?) {
        adapterScope.launch {
            withContext(Dispatchers.Main) {
                super.submitList(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AsteroidViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AsteroidViewHolder -> {
                val item = getItem(position)
                holder.bind(item, clickCallback)
            }
        }
    }
}
