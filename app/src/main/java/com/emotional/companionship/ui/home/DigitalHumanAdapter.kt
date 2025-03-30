package com.emotional.companionship.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emotional.companionship.R
import com.emotional.companionship.data.model.DigitalHuman

/**
 * 数字人和"添加数字人"卡片的混合适配器
 */
class DigitalHumanAdapter(
    private val onButtonClick: (DigitalHuman) -> Unit,
    private val onAddClick: () -> Unit
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
                DigitalHumanViewHolder(view)
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
                
                // 为第一个项目设置特殊的边距
                if (position == 0) {
                    val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
                    params.topMargin = -36
                    holder.itemView.layoutParams = params
                }
                
                // 设置按钮点击监听
                holder.btnStartChat.setOnClickListener {
                    Log.d("DigitalHumanAdapter", "按钮点击: ${item.name}")
                    onButtonClick(item)
                }
                
                // 确保内容区域不可点击
                holder.contentArea.isClickable = false
                holder.contentArea.isFocusable = false
            }
            is AddDigitalHumanViewHolder -> {
                // 设置整个卡片的点击事件
                holder.itemView.setOnClickListener {
                    Log.d("DigitalHumanAdapter", "添加卡片点击")
                    onAddClick()
                }
            }
        }
    }

    /**
     * 数字人项目的视图持有者
     */
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

    /**
     * 添加数字人卡片的视图持有者
     */
    inner class AddDigitalHumanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
} 