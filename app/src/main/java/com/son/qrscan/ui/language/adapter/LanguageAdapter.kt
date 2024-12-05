package com.son.qrscan.ui.language.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.son.qrscan.data.model.Language
import com.son.qrscan.databinding.ItemLanguageBinding

class LanguageAdapter : ListAdapter<Language, LanguageAdapter.LanguageViewHolder>(DIFF_CALLBACK) {

    var onItemClickListener: (Language) -> Unit = {}

    inner class LanguageViewHolder(val binding: ItemLanguageBinding) : ViewHolder(binding.root) {
        fun onBind(language: Language) {
            with(binding) {
                ivFlag.setImageResource(language.flag)
                tvName.setText(language.name)
            }

            binding.root.setOnClickListener {
                onItemClickListener(language)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        return LanguageViewHolder(
            ItemLanguageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.contains(PAYLOAD_SELECTED)) updateItemSelected(holder, getItem(position))
        else super.onBindViewHolder(holder, position, payloads)
    }

    private fun updateItemSelected(holder: LanguageViewHolder, item: Language) {
        with(holder) {
            binding.imgCheck.isSelected = item.isSelected
        }
    }

    companion object {
        const val PAYLOAD_SELECTED = "PAYLOAD_SELECTED"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Language>() {
            override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
                return oldItem.code == newItem.code
            }

            override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: Language, newItem: Language): Any? {
                return if (oldItem.isSelected != newItem.isSelected) PAYLOAD_SELECTED
                else super.getChangePayload(oldItem, newItem)
            }
        }
    }
}