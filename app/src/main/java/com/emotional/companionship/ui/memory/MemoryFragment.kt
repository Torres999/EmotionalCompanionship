package com.emotional.companionship.ui.memory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.emotional.companionship.data.model.MemoryItem
import com.emotional.companionship.databinding.FragmentMemoryBinding

class MemoryFragment : Fragment() {
    private var _binding: FragmentMemoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MemoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        loadMemories()
    }

    private fun setupRecyclerView() {
        adapter = MemoryAdapter()
        binding.rvMemories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMemories.adapter = adapter
    }

    private fun loadMemories() {
        // 模拟数据加载
        val memories = listOf(
            MemoryItem(
                id = "1",
                title = "第一次视频对话",
                content = "今天和妈妈进行了第一次视频对话，她看起来很开心...",
                date = "2024-03-15 14:30",
                imageUrl = "memory_avatar"
            ),
            MemoryItem(
                id = "2",
                title = "分享生活趣事",
                content = "和妈妈分享了今天的工作和生活，她给了我很多建议...",
                date = "2024-03-14 20:15",
                imageUrl = "memory_avatar"
            )
        )
        
        adapter.submitList(memories)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MemoryFragment()
    }
} 