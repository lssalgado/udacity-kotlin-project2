package com.udacity.asteroidradar.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.databinding.FragmentDetailBinding
import com.udacity.asteroidradar.detail.DetailFragmentArgs

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this


        val arguments = DetailFragmentArgs.fromBundle(requireArguments())
        val detailsViewModelFactory = DetailViewModelFactory(arguments.selectedAsteroid)

        val viewModel =
            ViewModelProvider(this, detailsViewModelFactory).get(DetailViewModel::class.java)

        binding.asteroid = viewModel.getAsteroid()

        return binding.root
    }
}