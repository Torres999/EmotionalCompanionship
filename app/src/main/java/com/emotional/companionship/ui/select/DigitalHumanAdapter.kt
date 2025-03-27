package com.emotional.companionship.ui.select

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emotional.companionship.R
import com.emotional.companionship.data.model.DigitalHuman

class DigitalHumanAdapter(private val onItemClick: (DigitalHuman) -> Unit) :
    ListAdapter<DigitalHuman, DigitalHumanAdapter.DigitalHumanViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigitalHumanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_digital_human, parent, false)
        return DigitalHumanViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: DigitalHumanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DigitalHumanViewHolder(
        itemView: View,
        private val onItemClick: (DigitalHuman) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvLastChat: TextView = itemView.findViewById(R.id.tvLastChat)
        private val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)

        fun bind(digitalHuman: DigitalHuman) {
            tvName.text = digitalHuman.name
            tvLastChat.text = "上次对话: ${digitalHuman.lastChatTime}"
            
            // 简化Glide使用，避免多余的配置
            try {
                // 使用try-catch包裹Glide调用，防止崩溃
                Glide.with(itemView.context)
                    .load(digitalHuman.avatarUrl.takeIf { !it.isNullOrEmpty() } ?: R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(ivAvatar)
            } catch (e: Exception) {
                // 如果Glide加载失败，直接设置默认图片
                ivAvatar.setImageResource(R.drawable.ic_person)
            }
            
            itemView.setOnClickListener {
                onItemClick(digitalHuman)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DigitalHuman>() {
            override fun areItemsTheSame(oldItem: DigitalHuman, newItem: DigitalHuman): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DigitalHuman, newItem: DigitalHuman): Boolean {
                return oldItem == newItem
            }
        }
    }
} 