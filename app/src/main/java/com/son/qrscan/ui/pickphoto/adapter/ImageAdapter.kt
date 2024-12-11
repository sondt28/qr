package com.son.qrscan.ui.pickphoto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.son.qrscan.data.model.Image
import com.son.qrscan.databinding.ItemImageBinding

class ImageAdapter: ListAdapter<Image, ImageAdapter.ImageViewHolder>(DIFF_CALLBACK) {
    var onImageClick: (Image) -> Unit = {}

    inner class ImageViewHolder( val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(image: Image) {
            with(binding) {
                Glide.with(root.context).load(image.image).into(ivPreview)
                vSelected.isVisible = image.isSelected
                root.setOnClickListener {
                    onImageClick(image)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: ImageViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.contains(PAYLOAD_SELECTED)) {
            updateItemSelected(holder, getItem(position))
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    private fun updateItemSelected(holder: ImageViewHolder, itemPosition: Image) {
        holder.binding.vSelected.isVisible = itemPosition.isSelected
    }

    companion object {
        const val PAYLOAD_SELECTED = "PAYLOAD_SELECTED"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: Image, newItem: Image): Any? {
                return if (oldItem.isSelected != newItem.isSelected) PAYLOAD_SELECTED
                else super.getChangePayload(oldItem, newItem)
            }
        }
    }
}