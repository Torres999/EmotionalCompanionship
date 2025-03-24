package com.emotional.companionship.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emotional.companionship.data.model.DigitalHuman
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _digitalHuman = MutableLiveData<DigitalHuman?>()
    val digitalHuman: LiveData<DigitalHuman?> = _digitalHuman

    init {
        // 模拟加载数据
        loadCurrentDigitalHuman()
    }

    private fun loadCurrentDigitalHuman() {
        // TODO: 从数据库加载当前选中的数字人
        // 这里先使用测试数据
        _digitalHuman.value = DigitalHuman(
            id = "1",
            name = "妈妈",
            avatarUrl = "https://example.com/avatar.jpg",
            relation = "妈妈",
            personality = "温柔体贴",
            lastChatTime = "2小时前"
        )
    }
} 