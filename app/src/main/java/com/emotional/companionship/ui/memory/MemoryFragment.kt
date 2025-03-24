package com.emotional.companionship.ui.memory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.emotional.companionship.databinding.FragmentMemoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemoryFragment : Fragment() {
    private var _binding: FragmentMemoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MemoryViewModel by viewModels()
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
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = MemoryAdapter()
        binding.rvMemories.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMemories.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.memories.observe(viewLifecycleOwner) { memories ->
            adapter.submitList(memories)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MemoryFragment()
    }
} 