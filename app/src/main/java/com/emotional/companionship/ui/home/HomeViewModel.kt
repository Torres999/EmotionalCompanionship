package com.emotional.companionship.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emotional.companionship.data.model.DigitalHuman
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _digitalHuman = MutableLiveData<DigitalHuman>()
    val digitalHuman: LiveData<DigitalHuman> = _digitalHuman
    
    private val _digitalHumans = MutableLiveData<List<DigitalHuman>>()
    val digitalHumans: LiveData<List<DigitalHuman>> = _digitalHumans
    
    init {
        loadMockData()
    }
    
    private fun loadMockData() {
        val mockHumans = listOf(
            DigitalHuman(
                id = "1",
                name = "妈妈",
                relation = "亲人",
                personality = "温柔、细心、关爱",
                avatarUrl = "https://example.com/avatar1.jpg",
                lastChatTime = "2小时前"
            ),
            DigitalHuman(
                id = "2",
                name = "爸爸",
                relation = "亲人",
                personality = "严厉、勤劳、负责任",
                avatarUrl = "https://example.com/avatar2.jpg",
                lastChatTime = "昨天"
            ),
            DigitalHuman(
                id = "3",
                name = "老师",
                relation = "导师",
                personality = "知识渊博、耐心、鼓励",
                avatarUrl = "https://example.com/avatar3.jpg",
                lastChatTime = "3天前"
            )
        )
        
        _digitalHumans.value = mockHumans
    }
} 