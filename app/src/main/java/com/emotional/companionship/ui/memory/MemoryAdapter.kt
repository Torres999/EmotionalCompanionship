package com.emotional.companionship.ui.memory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emotional.companionship.data.model.Memory
import com.emotional.companionship.databinding.ItemMemoryBinding

class MemoryAdapter : ListAdapter<Memory, MemoryAdapter.ViewHolder>(DiffCallback()) {

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

        fun bind(item: Memory) {
            binding.tvTitle.text = item.title
            binding.tvDateTime.text = item.dateTime
            binding.tvContent.text = item.content
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Memory>() {
        override fun areItemsTheSame(oldItem: Memory, newItem: Memory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Memory, newItem: Memory): Boolean {
            return oldItem == newItem
        }
    }
} 