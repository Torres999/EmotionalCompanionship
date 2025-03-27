package com.emotional.companionship.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emotional.companionship.data.model.User
import com.emotional.companionship.util.UserSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userSessionManager: UserSessionManager
) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    fun loginWithWechat() {
        _loadingState.value = true
        
        // 模拟微信登录过程
        viewModelScope.launch {
            delay(1500) // 模拟网络请求
            
            // 模拟成功获取用户信息
            val mockUser = User(
                id = UUID.randomUUID().toString(),
                userId = "wx_" + System.currentTimeMillis(),
                name = "微信用户",
                description = "",
                avatar = "https://example.com/default_avatar.jpg",
                email = ""
            )
            
            // 保存到本地会话
            userSessionManager.saveUser(mockUser)
            
            _loadingState.value = false
            _loginResult.value = true
        }
    }
} 