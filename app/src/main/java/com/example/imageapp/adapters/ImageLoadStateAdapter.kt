package com.example.imageapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.databinding.LoadingItemBinding

/**
 * Created by shande on 16-05-2021
 */
class ImageLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ImageLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(LoadingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    class LoadStateViewHolder(private val loadingItemBinding: LoadingItemBinding) : RecyclerView.ViewHolder(loadingItemBinding.root) {

        fun bindState(loadState: LoadState, retry: () -> Unit) {
            with(loadingItemBinding) {
                loadStateRetry.isVisible = loadState !is LoadState.Loading

                loadStateErrorMessage.isVisible = loadState !is LoadState.Loading

                loadStateProgress.isVisible = loadState is LoadState.Loading

                if (loadState is LoadState.Error) {
                    loadStateErrorMessage.text = loadState.error.localizedMessage
                }

                loadStateRetry.setOnClickListener {
                    retry.invoke()
                }
            }
        }
    }

}
