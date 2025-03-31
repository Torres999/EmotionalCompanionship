package com.emotional.companionship.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.emotional.companionship.R
import com.emotional.companionship.databinding.ActivitySplashBinding
import com.emotional.companionship.ui.select.SelectDigitalHumanActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()
    
    // 添加延迟时间常量
    private val SPLASH_DISPLAY_TIME = 500L // 0.5秒

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 设置状态栏颜色为标题栏背景色
        window.statusBarColor = ContextCompat.getColor(this, R.color.top_bar_color)
        
        // 使用延迟跳转，而不是立即跳转
//        Handler(Looper.getMainLooper()).postDelayed({
            // 自动跳转到SelectDigitalHumanActivity
            navigateToMainScreen()
//        }, SPLASH_DISPLAY_TIME)
        
        setupObservers()
    }
    
    /*
    private fun setupViews() {
        binding.btnStart.setOnClickListener {
            viewModel.onStartClick()
        }
    }
    */
    
    private fun navigateToMainScreen() {
        // 跳转到主界面
        SelectDigitalHumanActivity.start(this)
        // 关闭启动页
        finish()
    }
    
    private fun setupObservers() {
        viewModel.navigationEvent.observe(this) { event ->
            when (event) {
                is SplashViewModel.NavigationEvent.NavigateToOnboarding -> {
                    // 跳转到主页（选择数字孪生人页面）
                    SelectDigitalHumanActivity.start(this)
                    // 关闭启动页
                    finish()
                }
            }
        }
    }
} 