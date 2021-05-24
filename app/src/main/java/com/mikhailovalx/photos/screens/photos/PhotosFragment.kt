package com.mikhailovalx.photos.screens.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mikhailovalx.photos.APP_ACTIVITY
import com.mikhailovalx.photos.R
import com.mikhailovalx.photos.USERS_INPUT_QUERY
import com.mikhailovalx.photos.data.Photo
import com.mikhailovalx.photos.databinding.FragmentPhotosBinding


class PhotosFragment : Fragment() {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PhotosFragmentViewModel
    private var findByUserQuery: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotosBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        initialization()
        loadPhotos()
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(PhotosFragmentViewModel::class.java)
        binding.recyclerView.adapter = viewModel.photosAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(APP_ACTIVITY, 3)

        binding.btnGetPhotos.setOnClickListener {
            findByUserQuery = binding.edtSearchQuery.text.isNotEmpty()
            USERS_INPUT_QUERY = binding.edtSearchQuery.text.toString()
            loadPhotos()
        }
    }

    private fun loadPhotos() {
        viewModel.loadPhotos(findByUserQuery).observe(this,
            { list ->
                with(viewModel.photosAdapter) {
                    photos.clear()
                    photos.addAll(list)
                    notifyDataSetChanged()
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}