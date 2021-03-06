package com.mikhailovalx.photos.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.mikhailovalx.photos.MainActivity
import com.mikhailovalx.photos.R
import com.mikhailovalx.photos.databinding.FragmentStartBinding
import com.mikhailovalx.photos.screens.news.NewsFragment


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        // Photos
        binding.btnOpenPhotosPage.setOnClickListener {
            getMainActivity(activity)?.navController?.navigate(R.id.action_startFragment_to_photosFragment)
        }

        // News
        binding.btnOpenNewsPage.setOnClickListener {
            getMainActivity(activity)?.navController?.navigate(R.id.action_startFragment_to_newsFragment)
        }
    }

    private fun getMainActivity(activity: FragmentActivity?): MainActivity? =
        if (activity is MainActivity) activity else null

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}