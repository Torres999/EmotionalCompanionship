package com.emotional.companionship.ui.create

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emotional.companionship.data.model.DigitalHuman
import com.emotional.companionship.databinding.ItemSelectDigitalHumanBinding

class SelectExistingDigitalHumanAdapter(
    private val onItemClick: (DigitalHuman) -> Unit
) : ListAdapter<DigitalHuman, SelectExistingDigitalHumanAdapter.ViewHolder>(DiffCallback()) {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSelectDigitalHumanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val isSelected = position == selectedPosition

        holder.binding.root.apply {
            strokeColor = if (isSelected) 
                ContextCompat.getColor(context, android.R.color.holo_blue_light)
            else 
                ContextCompat.getColor(context, android.R.color.darker_gray)
            strokeWidth = if (isSelected) 2.dpToPx() else 1.dpToPx()
            setOnClickListener {
                onItemClick(item)
                setSelected(position)
            }
        }

        Glide.with(holder.binding.ivAvatar)
            .load(item.avatarUrl)
            .into(holder.binding.ivAvatar)
    }

    fun setSelected(position: Int) {
        val oldPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(oldPosition)
        notifyItemChanged(position)
    }

    class ViewHolder(
        val binding: ItemSelectDigitalHumanBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class DiffCallback : DiffUtil.ItemCallback<DigitalHuman>() {
        override fun areItemsTheSame(oldItem: DigitalHuman, newItem: DigitalHuman): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DigitalHuman, newItem: DigitalHuman): Boolean {
            return oldItem == newItem
        }
    }
}

fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt() 