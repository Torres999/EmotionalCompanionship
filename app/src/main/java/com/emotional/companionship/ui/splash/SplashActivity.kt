package com.emotional.companionship.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.emotional.companionship.R
import com.emotional.companionship.databinding.ActivitySplashBinding
import com.emotional.companionship.ui.select.SelectDigitalHumanActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 设置状态栏颜色为标题栏背景色
        window.statusBarColor = ContextCompat.getColor(this, R.color.top_bar_color)
        
        // 自动跳转到SelectDigitalHumanActivity
        navigateToMainScreen()
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
        // 直接跳转到主界面，不需要点击按钮
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