package com.emotional.companionship.ui.memory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emotional.companionship.data.model.MemoryItem
import com.emotional.companionship.databinding.ItemMemoryBinding

class MemoryAdapter : ListAdapter<MemoryItem, MemoryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMemoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemMemoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MemoryItem) {
            binding.tvTitle.text = item.title
            binding.tvDateTime.text = item.date
            binding.tvContent.text = item.content
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<MemoryItem>() {
        override fun areItemsTheSame(oldItem: MemoryItem, newItem: MemoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MemoryItem, newItem: MemoryItem): Boolean {
            return oldItem == newItem
        }
    }
} 