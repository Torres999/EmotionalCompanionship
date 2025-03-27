package com.emotional.companionship.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emotional.companionship.data.model.DigitalHuman

class SelectDigitalHumanViewModel : ViewModel() {
    private val _digitalHumans = MutableLiveData<List<DigitalHuman>>()
    val digitalHumans: LiveData<List<DigitalHuman>> = _digitalHumans

    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    init {
        loadDigitalHumans()
    }

    private fun loadDigitalHumans() {
        // 添加测试数据而不是空列表，使用空字符串代替网络URL，会使用默认本地资源
        _digitalHumans.value = listOf(
            DigitalHuman(
                id = "1",
                name = "女儿",
                relation = "女儿",
                personality = "温柔体贴，善解人意",
                avatarUrl = "",
                lastChatTime = "2025-03-24 10:30"
            ),
            DigitalHuman(
                id = "2",
                name = "儿子",
                relation = "儿子",
                personality = "严厉认真，不苟言笑",
                avatarUrl = "",
                lastChatTime = "2025-03-23 18:15"
            ),
            DigitalHuman(
                id = "3",
                name = "小明",
                relation = "朋友",
                personality = "活泼开朗，乐于助人",
                avatarUrl = "",
                lastChatTime = "2025-03-22 12:40"
            )
        )
    }

    fun onAddClick() {
        _navigationEvent.value = NavigationEvent.NavigateToCreate
    }

    fun onDigitalHumanClick(digitalHuman: DigitalHuman) {
        _navigationEvent.value = NavigationEvent.NavigateToChat(digitalHuman)
    }

    sealed class NavigationEvent {
        object NavigateToCreate : NavigationEvent()
        data class NavigateToChat(val digitalHuman: DigitalHuman) : NavigationEvent()
    }
} 