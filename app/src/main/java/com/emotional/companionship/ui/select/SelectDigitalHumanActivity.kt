package com.emotional.companionship.ui.select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.emotional.companionship.R
import com.emotional.companionship.databinding.ActivitySelectDigitalHumanBinding
import com.emotional.companionship.ui.create.CreateDigitalHumanActivity
import com.emotional.companionship.data.model.DigitalHuman
import com.emotional.companionship.ui.profile.ProfileFragment
import com.emotional.companionship.ui.webview.WebViewActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectDigitalHumanActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectDigitalHumanBinding
    private val viewModel: SelectDigitalHumanViewModel by viewModels()
    private val adapter = DigitalHumanAdapter { digitalHuman ->
        viewModel.onDigitalHumanClick(digitalHuman)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectDigitalHumanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 设置状态栏颜色为标题栏背景色
        window.statusBarColor = ContextCompat.getColor(this, R.color.top_bar_color)
        
        setupToolbar()
        setupViews()
        setupObservers()
    }
    
    private fun setupToolbar() {
        // 设置居中的标题视图
        val titleTextView = TextView(this)
        titleTextView.layoutParams = androidx.appcompat.widget.Toolbar.LayoutParams(
            androidx.appcompat.widget.Toolbar.LayoutParams.WRAP_CONTENT,
            androidx.appcompat.widget.Toolbar.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        )
        titleTextView.text = "首页"
        titleTextView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        titleTextView.textSize = 18f
        binding.toolbar.addView(titleTextView)
        
        // 存储标题视图的引用，以便后续更新
        binding.toolbar.tag = titleTextView
    }
    
    private fun updateTitle(title: String) {
        val titleTextView = binding.toolbar.tag as? TextView
        titleTextView?.text = title
    }

    private fun setupViews() {
        // 设置RecyclerView
        binding.rvDigitalHumans.layoutManager = LinearLayoutManager(this)
        binding.rvDigitalHumans.adapter = adapter
        
        // 设置底部导航
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // 显示主页内容，隐藏其他内容
                    showHomeContent()
                    updateTitle("首页")
                    true
                }
                R.id.navigation_history -> {
                    showHistoryContent()
                    updateTitle("记忆库")
                    true
                }
                R.id.navigation_profile -> {
                    // 显示个人中心内容
                    showProfileContent()
                    updateTitle("个人中心")
                    true
                }
                else -> false
            }
        }
        
        // 默认选中首页
        binding.bottomNavigation.selectedItemId = R.id.navigation_home
    }
    
    private fun showHomeContent() {
        // 显示主页内容，隐藏其他内容
        binding.clContent.visibility = View.VISIBLE
        binding.fragmentContainer.visibility = View.GONE
        
        // 移除所有Fragment
        removeAllFragments()
    }
    
    private fun showHistoryContent() {
        // 隐藏主页内容
        binding.clContent.visibility = View.GONE
        binding.fragmentContainer.visibility = View.VISIBLE
        
        // 移除所有Fragment
        removeAllFragments()
        
        // 添加记忆库Fragment
        var memoryFragment = supportFragmentManager.findFragmentByTag("memory_fragment") as? com.emotional.companionship.ui.memory.MemoryFragment
        
        if (memoryFragment == null) {
            memoryFragment = com.emotional.companionship.ui.memory.MemoryFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, memoryFragment, "memory_fragment")
                .commit()
        }
    }
    
    private fun showProfileContent() {
        // 隐藏主页内容
        binding.clContent.visibility = View.GONE
        binding.fragmentContainer.visibility = View.VISIBLE
        
        // 移除所有Fragment
        removeAllFragments()
        
        // 添加个人中心Fragment
        var profileFragment = supportFragmentManager.findFragmentByTag("profile_fragment") as? ProfileFragment
        
        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, profileFragment, "profile_fragment")
                .commit()
        }
    }
    
    private fun removeAllFragments() {
        val fragments = arrayOf(
            supportFragmentManager.findFragmentByTag("profile_fragment"),
            supportFragmentManager.findFragmentByTag("memory_fragment")
        )
        
        supportFragmentManager.beginTransaction().apply {
            fragments.filterNotNull().forEach { remove(it) }
            if (fragments.any { it != null }) {
                commit()
            }
        }
    }

    private fun setupObservers() {
        viewModel.digitalHumans.observe(this) { digitalHumans ->
            adapter.submitList(digitalHumans)
        }

        viewModel.navigationEvent.observe(this) { event ->
            when (event) {
                is SelectDigitalHumanViewModel.NavigationEvent.NavigateToCreate -> {
                    CreateDigitalHumanActivity.start(this)
                }
                is SelectDigitalHumanViewModel.NavigationEvent.NavigateToChat -> {
                    // 无需传递digitalHuman参数
                    showConfirmDialog()
                }
            }
        }
    }
    
    private fun showConfirmDialog() {
        try {
            // 使用自定义对话框
            com.emotional.companionship.ui.dialog.ConfirmDialog.show(
                context = this,
                message = "是否开始视频对话",
                confirmText = "确认",
                onConfirm = {
                    openBingWebsite()
                }
            )
        } catch (e: Exception) {
            Toast.makeText(this, "无法显示对话框: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun openBingWebsite() {
        try {
            // 使用WebView打开页面，而不是调用外部浏览器
            val intent = Intent(this, WebViewActivity::class.java).apply {
                putExtra("url", "https://www.bing.com")
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "功能暂不可用", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SelectDigitalHumanActivity::class.java))
        }
    }
    
    // 添加这个方法，供适配器调用以添加新的数字人
    fun showAddDigitalHuman() {
        viewModel.onAddClick()
    }
} 