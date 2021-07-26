package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.main.adapter.MainAdapter
import timber.log.Timber

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            MainViewModelFactory(activity.application)
        ).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val clickCallback = MainAdapter.ClickCallback {
            viewModel.onAsteroidClicked(it)
        }
        val adapter = MainAdapter(clickCallback)

        binding.asteroidRecycler.adapter = adapter
        viewModel.asteroids.observe(viewLifecycleOwner, Observer { asteroidList ->
            adapter.submitList(asteroidList)
            asteroidList.forEach {
                Timber.e(it.toString())
            }
        })

        viewModel.navigateToAsteroidDetails.observe(viewLifecycleOwner, Observer { asteroid ->
            asteroid?.let {
                findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.onAsteroidDetailsNavigated()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }
}
