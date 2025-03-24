package com.emotional.companionship.ui.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emotional.companionship.data.model.DigitalHuman
import com.emotional.companionship.databinding.ItemDigitalHumanBinding

class DigitalHumanAdapter(
    private val onItemClick: (DigitalHuman) -> Unit
) : ListAdapter<DigitalHuman, DigitalHumanAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemDigitalHumanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemDigitalHumanBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(item: DigitalHuman) {
            binding.tvName.text = item.name
            Glide.with(binding.ivAvatar)
                .load(item.avatarUrl)
                .into(binding.ivAvatar)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<DigitalHuman>() {
        override fun areItemsTheSame(oldItem: DigitalHuman, newItem: DigitalHuman): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DigitalHuman, newItem: DigitalHuman): Boolean {
            return oldItem == newItem
        }
    }
} 