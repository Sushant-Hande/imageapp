package com.example.imageapp.screens.images

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.imageapp.R
import com.example.imageapp.adapters.ImageAdapter
import com.example.imageapp.adapters.ImageLoadStateAdapter
import com.example.imageapp.databinding.ImagesActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImagesActivity : AppCompatActivity() {
    lateinit var binding: ImagesActivityBinding
    private val viewModel: ImagesViewModel by viewModels()
    private lateinit var imagesAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.images_activity)

        imagesAdapter = ImageAdapter()

        val stateAdapter = ImageLoadStateAdapter { imagesAdapter.retry() }

        binding.rvImages.adapter = imagesAdapter.withLoadStateFooter(stateAdapter)

        binding.rvImages.setHasFixedSize(true)

        val gridLayoutManager = GridLayoutManager(this, 2)

        binding.rvImages.layoutManager = gridLayoutManager

        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // If progress will be shown then span size will be 1 otherwise it will be 2
                return if (imagesAdapter.getItemViewType(position) == ImageAdapter.LOADING_ITEM) 1 else 2
            }
        }

        observerViewModel()
    }

    private fun observerViewModel() {
        lifecycleScope.launch {
            viewModel.images.collectLatest { pagedData ->
                imagesAdapter.submitData(pagedData)
            }
        }
    }

}