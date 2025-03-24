package com.emotional.companionship.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.emotional.companionship.R
import com.emotional.companionship.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.llPersonalInfo.setOnClickListener {
            // TODO: Navigate to personal info screen
        }

        binding.llPrivacySettings.setOnClickListener {
            // TODO: Navigate to privacy settings screen
        }

        binding.llFeedback.setOnClickListener {
            // TODO: Navigate to feedback screen
        }

        binding.llAboutUs.setOnClickListener {
            // TODO: Navigate to about us screen
        }
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.tvName.text = user.name
            binding.tvDescription.text = user.description
            // 使用Glide加载头像
            Glide.with(this).load(user.avatar).placeholder(R.drawable.ic_avatar_placeholder).into(binding.ivAvatar)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
} 