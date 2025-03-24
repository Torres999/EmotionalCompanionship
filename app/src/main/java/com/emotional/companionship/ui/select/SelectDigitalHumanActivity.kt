package com.emotional.companionship.ui.select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emotional.companionship.R
import com.emotional.companionship.databinding.ActivitySelectDigitalHumanBinding
import com.emotional.companionship.ui.create.CreateDigitalHumanActivity
import com.emotional.companionship.data.model.DigitalHuman
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        // 设置RecyclerView
        binding.rvDigitalHumans.layoutManager = LinearLayoutManager(this)
        binding.rvDigitalHumans.adapter = adapter
        
        // 设置底部导航
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // 已经在首页，无需操作
                    true
                }
                R.id.navigation_history -> {
                    Toast.makeText(this, "记忆库功能开发中", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_profile -> {
                    Toast.makeText(this, "个人中心功能开发中", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        
        // 默认选中首页
        binding.bottomNavigation.selectedItemId = R.id.navigation_home
        
        // 添加FAB点击事件
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd?.setOnClickListener {
            viewModel.onAddClick()
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
                    Toast.makeText(this, "即将与${event.digitalHuman.name}聊天", Toast.LENGTH_SHORT).show()
                    // TODO: Navigate to chat screen
                }
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SelectDigitalHumanActivity::class.java))
        }
    }
} 