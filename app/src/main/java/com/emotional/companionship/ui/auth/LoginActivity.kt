package com.emotional.companionship.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emotional.companionship.databinding.ActivityLoginBinding
import com.emotional.companionship.ui.create.CreateDigitalHumanActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        // 微信登录按钮
        binding.btnWechatLogin.setOnClickListener {
            viewModel.loginWithWechat()
        }

        // 返回按钮
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
                navigateToNextScreen()
            } else {
                Toast.makeText(this, "登录失败，请重试", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadingState.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
            binding.btnWechatLogin.isEnabled = !isLoading
        }
    }

    private fun navigateToNextScreen() {
        // 判断是否有需要返回的目标页面
        val hasTarget = intent.hasExtra(EXTRA_TARGET_CLASS)
        if (hasTarget) {
            val targetClassName = intent.getStringExtra(EXTRA_TARGET_CLASS)
            try {
                val targetClass = Class.forName(targetClassName!!)
                startActivity(Intent(this, targetClass))
            } catch (e: Exception) {
                // 默认返回创建数字人页面
                CreateDigitalHumanActivity.start(this)
            }
        } else {
            // 默认返回创建数字人页面
            CreateDigitalHumanActivity.start(this)
        }
        finish()
    }

    companion object {
        private const val EXTRA_TARGET_CLASS = "extra_target_class"

        fun start(context: Context, targetClass: Class<*>? = null) {
            val intent = Intent(context, LoginActivity::class.java)
            targetClass?.let {
                intent.putExtra(EXTRA_TARGET_CLASS, it.name)
            }
            context.startActivity(intent)
        }
    }
} 