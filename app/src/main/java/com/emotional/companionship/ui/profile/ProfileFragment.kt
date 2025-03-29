package com.emotional.companionship.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        
        // 直接设置默认头像，避免Glide初始化问题
        binding.ivAvatar.setImageResource(R.drawable.ic_person)
    }

    private fun setupViews() {
        // 设置菜单项点击事件
        binding.llAccountSettings.setOnClickListener {
            Toast.makeText(requireContext(), "账号设置", Toast.LENGTH_SHORT).show()
        }
        
        binding.llRecharge.setOnClickListener {
            Toast.makeText(requireContext(), "充值", Toast.LENGTH_SHORT).show()
        }

        binding.llPrivacySettings.setOnClickListener {
            Toast.makeText(requireContext(), "隐私设置", Toast.LENGTH_SHORT).show()
        }

        binding.llNotificationSettings.setOnClickListener {
            Toast.makeText(requireContext(), "通知设置", Toast.LENGTH_SHORT).show()
        }

        binding.llAboutUs.setOnClickListener {
            Toast.makeText(requireContext(), "关于我们", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.tvName.text = user.name
            binding.tvUserId.text = "ID: ${user.userId}"
            
            // 简化头像加载，直接使用本地资源
            binding.ivAvatar.setImageResource(R.drawable.ic_person)
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