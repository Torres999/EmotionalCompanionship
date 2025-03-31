package com.emotional.companionship.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import com.emotional.companionship.R

class ConfirmDialog(
    context: Context,
    private val message: String,
    private val confirmText: String = "删除",
    private val onConfirm: () -> Unit,
    private val onCancel: () -> Unit = {}
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_confirm)
        
        // 设置对话框属性
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            
            // 设置对话框位置和大小
            val params = attributes
            params.gravity = Gravity.CENTER  // 设置重力为居中
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            // 设置对话框动画和窗口标志
            params.windowAnimations = android.R.style.Animation_Dialog
            attributes = params
        }
        
        // 设置点击外部不关闭
        setCanceledOnTouchOutside(false)
        
        // 初始化视图
        initViews()
    }
    
    private fun initViews() {
        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val btnCancel = findViewById<TextView>(R.id.btnCancel)
        val btnConfirm = findViewById<TextView>(R.id.btnConfirm)
        
        // 设置消息文本
        tvMessage.text = message
        
        // 设置确认按钮文本
        btnConfirm.text = confirmText
        
        // 设置取消按钮点击事件
        btnCancel.setOnClickListener {
            onCancel()
            dismiss()
        }
        
        // 设置确认按钮点击事件
        btnConfirm.setOnClickListener {
            onConfirm()
            dismiss()
        }
    }
    
    companion object {
        fun show(
            context: Context,
            message: String = "是否开始视频对话",
            confirmText: String = "删除",
            onConfirm: () -> Unit,
            onCancel: () -> Unit = {}
        ): ConfirmDialog {
            return ConfirmDialog(context, message, confirmText, onConfirm, onCancel).apply {
                show()
            }
        }
    }
} 