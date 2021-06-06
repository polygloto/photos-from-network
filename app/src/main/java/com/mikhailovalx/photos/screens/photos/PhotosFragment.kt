package com.mikhailovalx.photos.screens.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mikhailovalx.photos.databinding.FragmentPhotosBinding


class PhotosFragment : Fragment() {

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PhotosFragmentViewModel

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
        binding.rvPhotos.adapter = viewModel.photosAdapter
        binding.rvPhotos.layoutManager = GridLayoutManager(context, 3)

        binding.btnGetPhotos.setOnClickListener {
            loadPhotos(usersQuery = binding.edtSearchQuery.text.trim().toString())
        }
    }

    private fun loadPhotos(usersQuery: String = "") {
        viewModel.loadPhotos(usersQuery).observe(this,
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