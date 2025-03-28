package com.emotional.companionship.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import androidx.core.content.ContextCompat
import com.emotional.companionship.R

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val backgroundColor = Color(ContextCompat.getColor(context, R.color.gray_light))
    val cardColor = Color(ContextCompat.getColor(context, R.color.white))
    val textPrimaryColor = Color(ContextCompat.getColor(context, R.color.text_primary))
    val textSecondaryColor = Color(ContextCompat.getColor(context, R.color.text_secondary))
    val dividerColor = Color(ContextCompat.getColor(context, R.color.divider))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        // 用户信息卡片
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
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
                        tint = cardColor
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // 用户信息
                Column {
                    Text(
                        text = "用户名",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = textPrimaryColor
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ID: 888888",
                        fontSize = 14.sp,
                        color = textSecondaryColor
                    )
                }
            }
        }

        // 第一个卡片：账号设置和通知设置
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                // 账号设置
                SettingItem(title = "账号设置", textPrimaryColor) {
                    // 点击事件
                }

                HorizontalDivider(color = dividerColor, thickness = 1.dp)

                // 通知设置
                SettingItem(title = "通知设置", textPrimaryColor) {
                    // 点击事件
                }
            }
        }

        // 第二个卡片：充值
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            // 充值
            SettingItem(title = "充值", textPrimaryColor) {
                // 点击事件
            }
        }

        // 第三个卡片：隐私设置和关于我们
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                // 隐私设置
                SettingItem(title = "隐私设置", textPrimaryColor) {
                    // 点击事件
                }

                HorizontalDivider(color = dividerColor, thickness = 1.dp)

                // 关于我们
                SettingItem(title = "关于我们", textPrimaryColor) {
                    // 点击事件
                }
            }
        }
    }
}

@Composable
fun SettingItem(title: String, textColor: Color, onClick: () -> Unit) {
    val context = LocalContext.current
    val grayColor = Color(ContextCompat.getColor(context, R.color.gray))

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
            color = textColor
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "进入",
            tint = grayColor,
            modifier = Modifier.size(24.dp)
        )
    }
} 