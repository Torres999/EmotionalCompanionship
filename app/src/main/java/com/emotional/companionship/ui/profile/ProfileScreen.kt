package com.emotional.companionship.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emotional.companionship.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // 用户信息卡片
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 头像
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = "用户头像",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // 用户信息
                Column {
                    Text(
                        text = "用户名",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ID: 888888",
                        fontSize = 14.sp,
                        color = Color(0xFF999999)
                    )
                }
            }
        }

        // 设置项列表
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                // 账号设置
                SettingItem(title = "账号设置") {
                    // 点击事件
                }

                Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)

                // 隐私设置
                SettingItem(title = "隐私设置") {
                    // 点击事件
                }

                Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)

                // 通知设置
                SettingItem(title = "通知设置") {
                    // 点击事件
                }

                Divider(color = Color(0xFFF0F0F0), thickness = 1.dp)

                // 关于我们
                SettingItem(title = "关于我们") {
                    // 点击事件
                }
            }
        }
    }
}

@Composable
fun SettingItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color(0xFF333333)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "进入",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
} 