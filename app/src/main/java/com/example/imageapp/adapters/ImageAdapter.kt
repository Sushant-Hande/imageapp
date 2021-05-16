package com.example.imageapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.imageapp.R
import com.example.imageapp.databinding.ImageItemBinding
import com.example.imageapp.models.ImageModel

/**
 * Created by shande on 16-05-2021
 */
class ImageAdapter : PagingDataAdapter<ImageModel, ImageAdapter.ImageViewHolder>(ImagesComparator) {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ImageAdapter.ImageViewHolder {
        return ImageViewHolder(
                ImageItemBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: ImageAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bindPassenger(it) }
    }

    inner class ImageViewHolder(private val binding: ImageItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindPassenger(item: ImageModel) = with(binding) {
            ivImage.load(item.urls.regular) {
                placeholder(R.drawable.ic_placeholder)
                diskCachePolicy(CachePolicy.ENABLED)
                networkCachePolicy(CachePolicy.ENABLED)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            IMAGE_ITEM
        } else{
            LOADING_ITEM
        }
    }

    object ImagesComparator : DiffUtil.ItemCallback<ImageModel>() {
        override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem.equals(newItem)
        }
    }

    companion object {
        const val LOADING_ITEM = 0
        const val IMAGE_ITEM = 1
    }

}