package com.son.qrscan.ui.pickphoto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.son.qrscan.data.model.ImageFolder
import com.son.qrscan.databinding.ItemFolderBinding

class FolderAdapter: ListAdapter<ImageFolder, FolderAdapter.FolderViewHolder>(DIFF_CALLBACK) {

    var onFolderClick: (ImageFolder) -> Unit = {}

    inner class FolderViewHolder(private val binding: ItemFolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageFolder: ImageFolder) {
            with(binding) {
                tvTitleFolder.text = imageFolder.name
                tvCount.text = root.context.getString(com.son.common.R.string.gallery_count, imageFolder.images.size)
                Glide.with(binding.root.context).load(imageFolder.images[0].image).into(ivThumbnail)
                root.setOnClickListener {
                    onFolderClick(imageFolder)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(ItemFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImageFolder>() {
            override fun areItemsTheSame(oldItem: ImageFolder, newItem: ImageFolder): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ImageFolder, newItem: ImageFolder): Boolean {
                return oldItem == newItem
            }
        }
    }
}