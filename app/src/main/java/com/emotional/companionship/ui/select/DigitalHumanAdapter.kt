package com.emotional.companionship.ui.select

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emotional.companionship.data.model.DigitalHuman
import com.emotional.companionship.databinding.ItemDigitalHumanBinding

class DigitalHumanAdapter(
    private val listener: DigitalHumanClickListener
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

        fun bind(item: DigitalHuman) {
            binding.item = item
            binding.listener = listener
            binding.executePendingBindings()
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