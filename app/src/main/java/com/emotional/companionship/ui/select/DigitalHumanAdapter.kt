package com.emotional.companionship.ui.select

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emotional.companionship.R
import com.emotional.companionship.data.model.DigitalHuman

/**
 * 数字人和"添加数字人"卡片的混合适配器
 */
class DigitalHumanAdapter(
    private val onItemClick: (DigitalHuman) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 视图类型常量
    private val ITEM_TYPE_DIGITAL_HUMAN = 0
    private val ITEM_TYPE_ADD = 1
    
    // 数据源
    private var digitalHumans: List<DigitalHuman> = emptyList()

    /**
     * 提交新的数据列表
     */
    fun submitList(list: List<DigitalHuman>) {
        digitalHumans = list
        notifyDataSetChanged()
        Log.d("DigitalHumanAdapter", "提交列表，数据项数: ${list.size}，总项数: ${list.size + 1}")
    }

    /**
     * 获取项目类型 - 数字人或添加按钮
     */
    override fun getItemViewType(position: Int): Int {
        return if (position < digitalHumans.size) {
            ITEM_TYPE_DIGITAL_HUMAN
        } else {
            ITEM_TYPE_ADD
        }
    }

    /**
     * 获取总项目数 - 数字人列表 + 添加按钮
     */
    override fun getItemCount(): Int {
        val count = digitalHumans.size + 1
        Log.d("DigitalHumanAdapter", "项目总数: $count (数字人: ${digitalHumans.size}, 添加按钮: 1)")
        return count
    }

    /**
     * 创建视图持有者
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("DigitalHumanAdapter", "创建视图持有者，类型: ${if (viewType == ITEM_TYPE_DIGITAL_HUMAN) "数字人" else "添加按钮"}")
        return when (viewType) {
            ITEM_TYPE_DIGITAL_HUMAN -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_digital_human, parent, false)
                DigitalHumanViewHolder(view, onItemClick)
            }
            ITEM_TYPE_ADD -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_add_digital_human, parent, false)
                AddDigitalHumanViewHolder(view)
            }
            else -> throw IllegalArgumentException("无效的视图类型: $viewType")
        }
    }

    /**
     * 绑定视图持有者
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("DigitalHumanAdapter", "绑定视图持有者，位置: $position, 类型: ${if (position < digitalHumans.size) "数字人" else "添加按钮"}")
        when (holder) {
            is DigitalHumanViewHolder -> {
                val item = digitalHumans[position]
                holder.bind(item)
            }
            is AddDigitalHumanViewHolder -> {
                // 设置整个卡片的点击事件
                holder.itemView.setOnClickListener {
                    Log.d("DigitalHumanAdapter", "添加卡片点击")
                    // 直接触发添加新数字人的操作
                    val context = holder.itemView.context
                    if (context is SelectDigitalHumanActivity) {
                        context.showAddDigitalHuman()
                    }
                }
            }
        }
    }

    /**
     * 数字人项目的视图持有者
     */
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

    /**
     * 添加数字人卡片的视图持有者
     */
    inner class AddDigitalHumanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
} 