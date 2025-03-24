package com.emotional.companionship.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emotional.companionship.databinding.FragmentHomeBinding
import com.emotional.companionship.ui.create.CreateDigitalHumanActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.btnStartChat.setOnClickListener {
            // 启动创建数字人的Activity
            CreateDigitalHumanActivity.start(requireContext())
        }
    }

    private fun observeViewModel() {
        viewModel.digitalHuman.observe(viewLifecycleOwner) { digitalHuman ->
            digitalHuman?.let {
                binding.tvName.text = it.name
                binding.tvLastChat.text = "上次对话：${it.lastChatTime}"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
} 