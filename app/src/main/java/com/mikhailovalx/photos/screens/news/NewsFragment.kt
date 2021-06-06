package com.mikhailovalx.photos.screens.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mikhailovalx.photos.R
import com.mikhailovalx.photos.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        initialization()
        loadNews()
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(NewsFragmentViewModel::class.java)
        binding.rvNews.adapter = viewModel.newsAdapter
    }


    private fun loadNews() {
        viewModel.newsListLiveData.observe(this,
            { list ->
                with(viewModel.newsAdapter) {
                    news.clear()
                    news.addAll(list)
                    notifyDataSetChanged()
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}