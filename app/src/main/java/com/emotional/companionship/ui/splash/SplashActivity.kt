package com.emotional.companionship.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.emotional.companionship.databinding.ActivitySplashBinding
import com.emotional.companionship.ui.select.SelectDigitalHumanActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        
        setupObservers()
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