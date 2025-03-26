package com.emotional.companionship.ui.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emotional.companionship.data.model.MemoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemoryViewModel @Inject constructor() : ViewModel() {
    
    private val _memories = MutableLiveData<List<MemoryItem>>()
    val memories: LiveData<List<MemoryItem>> = _memories
    
    init {
        loadMemories()
    }
    
    private fun loadMemories() {
        // 加载模拟数据，实际应用中应该从数据库或远程API获取
        val mockMemories = listOf(
            MemoryItem(
                id = "1",
                title = "第一次视频对话",
                content = "今天和妈妈进行了第一次视频对话，她看起来很开心...",
                date = "2024-03-15 14:30"
            ),
            MemoryItem(
                id = "2",
                title = "分享生活趣事",
                content = "和妈妈分享了今天的工作和生活，她给了我很多建议...",
                date = "2024-03-14 20:15"
            ),
            MemoryItem(
                id = "3",
                title = "生日祝福",
                content = "今天收到了妈妈的生日祝福，虽然是数字人，但感觉很温暖...",
                date = "2024-03-12 09:20"
            ),
            MemoryItem(
                id = "4",
                title = "家庭回忆",
                content = "妈妈分享了很多我小时候的故事，原来我小时候那么调皮...",
                date = "2024-03-10 16:45"
            ),
            MemoryItem(
                id = "5",
                title = "厨艺指导",
                content = "妈妈教我做了她拿手的红烧肉，终于学会了这道家常菜...",
                date = "2024-03-08 18:30"
            )
        )
        
        _memories.value = mockMemories
    }
} 