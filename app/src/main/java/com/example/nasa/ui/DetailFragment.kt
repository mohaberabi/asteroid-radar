package com.example.nasa.ui

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.nasa.R
import com.example.nasa.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_detail,
                container,
                false
            )

        val asteroid = DetailFragmentArgs.fromBundle(requireArguments()).asteroid
        binding.asteroid = asteroid


        binding.infoButton.setOnClickListener {

            val dialogInflator = LayoutInflater.from(context).inflate(R.layout.dialog, null)


            val dialog = AlertDialog.Builder(context)
                .setView(dialogInflator).show()
            val accpetButton = dialog.findViewById<TextView>(R.id.accpetButton)

            accpetButton.setOnClickListener {
                dialog.dismiss()
            }
        }
        return binding.root
    }

}