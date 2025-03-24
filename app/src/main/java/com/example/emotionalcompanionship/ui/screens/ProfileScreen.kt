package com.example.emotionalcompanionship.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 用户信息区域
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 头像占位
                Surface(
                    modifier = Modifier.size(64.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    // 这里后续会添加用户头像
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
                    Text(
                        text = "用户名",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "ID: 123456789",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // 功能列表
        ListItem(
            headlineContent = { Text("我的收藏") },
            modifier = Modifier.clickable { }
        )
        
        ListItem(
            headlineContent = { Text("通知设置") },
            modifier = Modifier.clickable { }
        )
        
        ListItem(
            headlineContent = { Text("隐私设置") },
            modifier = Modifier.clickable { }
        )
        
        ListItem(
            headlineContent = { Text("关于我们") },
            modifier = Modifier.clickable { }
        )
    }
} 