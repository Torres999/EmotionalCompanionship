package com.emotional.companionship

import android.app.Application
import android.webkit.WebView
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmotionalCompanionshipApp : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // 设置WebView数据目录后缀，避免每次创建WebView时设置
        try {
            WebView.setDataDirectorySuffix("emotional_companionship")
        } catch (e: Exception) {
            // 忽略可能的异常，如WebView已初始化
        }
    }
} 