package com.emotional.companionship.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emotional.companionship.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user
    
    init {
        // 加载用户数据
        loadUserData()
    }
    
    private fun loadUserData() {
        // 模拟从数据库或网络获取用户数据
        _user.value = User(
            id = "1",
            name = "张三",
            description = "情感陪伴用户",
            avatar = "",
            email = "zhangsan@example.com"
        )
    }
} 