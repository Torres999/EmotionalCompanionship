package com.emotional.companionship.ui.memory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emotional.companionship.R
import com.emotional.companionship.data.model.MemoryItem
import com.emotional.companionship.databinding.ItemMemoryBinding

class MemoryAdapter : ListAdapter<MemoryItem, MemoryAdapter.MemoryViewHolder>(MemoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val binding = ItemMemoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MemoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MemoryViewHolder(private val binding: ItemMemoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memory: MemoryItem) {
            binding.apply {
                tvMemoryTitle.text = memory.title
                tvMemoryTime.text = memory.date
                tvMemoryContent.text = memory.content
                
                // 设置头像图片
                val resourceId = if (memory.imageUrl != null) {
                    itemView.context.resources.getIdentifier(
                        memory.imageUrl, "drawable", itemView.context.packageName
                    )
                } else {
                    0
                }
                
                if (resourceId != 0) {
                    ivMemoryAvatar.setImageResource(resourceId)
                } else {
                    ivMemoryAvatar.setImageResource(R.drawable.ic_person)
                }
            }
        }
    }

    private class MemoryDiffCallback : DiffUtil.ItemCallback<MemoryItem>() {
        override fun areItemsTheSame(oldItem: MemoryItem, newItem: MemoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MemoryItem, newItem: MemoryItem): Boolean {
            return oldItem == newItem
        }
    }
} 