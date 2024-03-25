package com.example.nasa.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nasa.R
import com.example.nasa.database.AsteroidDBModel
import com.example.nasa.databinding.FragmentAsteroidBinding
import com.example.nasa.util.Constants
import com.example.nasa.viewmodels.AsteroidViewModel
import com.example.nasa.viewmodels.FilterEnum
import java.util.Calendar


class AsteroidFragment : Fragment() {
    private lateinit var asteroidViewModel: AsteroidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentAsteroidBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_asteroid,
                container,
                false
            )

        val application = requireNotNull(activity).application

        val factory = AsteroidViewModel.Factory(application)
        asteroidViewModel = ViewModelProvider(this, factory)[AsteroidViewModel::class.java]


        binding.viewModel = asteroidViewModel
        binding.lifecycleOwner = this

        binding.recView.adapter = AsteroidAdapter(

            AsteroidAdapter.AsteroidClickListener {

                findNavController().navigate(
                    AsteroidFragmentDirections.actionAsteroidFragmentToDetailFragment(
                        it
                    )
                )

            })


        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var filter: FilterEnum = FilterEnum.ALL
        when (item.itemId) {
            R.id.all -> filter = FilterEnum.ALL
            R.id.today -> filter = FilterEnum.TODAY
            R.id.week -> filter = FilterEnum.WEEK
        }
        asteroidViewModel.onFilterRequested(filter)
        return true
    }
}