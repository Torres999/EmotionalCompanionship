package com.emotional.companionship.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emotional.companionship.R
import com.emotional.companionship.data.model.DigitalHuman

class DigitalHumanAdapter(
    private val onButtonClick: (DigitalHuman) -> Unit
) : ListAdapter<DigitalHuman, DigitalHumanAdapter.DigitalHumanViewHolder>(DigitalHumanDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigitalHumanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_digital_human, parent, false)
        return DigitalHumanViewHolder(view)
    }

    override fun onBindViewHolder(holder: DigitalHumanViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        
        // 为第一个项目设置特殊的边距
        if (position == 0) {
            val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
            params.topMargin = -36 // 使用更大的负外边距使第一个项目更靠近顶部
            holder.itemView.layoutParams = params
        }
        
        // 仅设置按钮点击监听
        holder.btnStartChat.setOnClickListener {
            Log.d("DigitalHumanAdapter", "Button clicked for: ${item.name}")
            onButtonClick(item)
        }
        
        // 确保内容区域不可点击
        holder.contentArea.isClickable = false
        holder.contentArea.isFocusable = false
    }

    inner class DigitalHumanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentArea: LinearLayout = itemView.findViewById(R.id.cardContentArea)
        val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvLastChat: TextView = itemView.findViewById(R.id.tvLastChat)
        val btnStartChat: Button = itemView.findViewById(R.id.btnStartChat)

        fun bind(digitalHuman: DigitalHuman) {
            tvName.text = digitalHuman.name
            tvLastChat.text = "上次对话：${digitalHuman.lastChatTime}"
            
            // 设置头像
            ivAvatar.setImageResource(R.drawable.ic_launcher_foreground)
            
            // 确保所有子视图不可点击
            ivAvatar.isClickable = false
            ivAvatar.isFocusable = false
            tvName.isClickable = false
            tvName.isFocusable = false
            tvLastChat.isClickable = false
            tvLastChat.isFocusable = false
        }
    }

    class DigitalHumanDiffCallback : DiffUtil.ItemCallback<DigitalHuman>() {
        override fun areItemsTheSame(oldItem: DigitalHuman, newItem: DigitalHuman): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DigitalHuman, newItem: DigitalHuman): Boolean {
            return oldItem == newItem
        }
    }
} 