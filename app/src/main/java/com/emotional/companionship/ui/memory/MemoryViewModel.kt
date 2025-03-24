package com.emotional.companionship.ui.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emotional.companionship.data.model.Memory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemoryViewModel @Inject constructor() : ViewModel() {
    private val _memories = MutableLiveData<List<Memory>>()
    val memories: LiveData<List<Memory>> = _memories

    init {
        // 模拟加载数据
        loadMemories()
    }

    private fun loadMemories() {
        // TODO: 从数据库加载记忆数据
        // 这里先使用测试数据
        _memories.value = listOf(
            Memory(
                id = "1",
                title = "第一次见面聊天",
                dateTime = "2024-01-13 13:43",
                content = "聊到了家乡的小吃和童年的趣事等，感觉聊得很开心。"
            ),
            Memory(
                id = "2",
                title = "看照片的回忆",
                dateTime = "2024-02-22 13:43", 
                content = "妈妈帮我看了家里的老照片，讲述了很多我小时候的故事..."
            )
        )
    }
} 