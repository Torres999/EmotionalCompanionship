package com.emotional.companionship.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emotional.companionship.R
import com.emotional.companionship.data.model.DigitalHuman
import com.emotional.companionship.databinding.FragmentHomeBinding
import com.emotional.companionship.ui.create.CreateDigitalHumanActivity
import com.emotional.companionship.util.UserSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: DigitalHumanAdapter
    
    private val mainHandler = Handler(Looper.getMainLooper())
    
    @Inject
    lateinit var userSessionManager: UserSessionManager

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
        setupRecyclerView()
        setupViews()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        Log.d("HomeFragment", "Setting up RecyclerView")
        adapter = DigitalHumanAdapter(
            onButtonClick = { digitalHuman ->
                activity?.runOnUiThread {
                    if (isAdded && !isDetached) {
                        showDialog(digitalHuman)
                    }
                }
            }
        )
        binding.rvDigitalHumans.adapter = adapter
    }

    private fun setupViews() {
        binding.fabAddDigitalHuman.setOnClickListener {
            CreateDigitalHumanActivity.start(requireContext())
        }
    }

    private fun observeViewModel() {
        viewModel.digitalHumans.observe(viewLifecycleOwner) { digitalHumans ->
            Log.d("HomeFragment", "Received ${digitalHumans.size} digital humans")
            adapter.submitList(digitalHumans)
        }
    }
    
    private fun showDialog(digitalHuman: DigitalHuman) {
        Log.d("HomeFragment", "Showing dialog for: ${digitalHuman.name}")
        try {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("开始对话")
                setMessage("是否开始与${digitalHuman.name}对话？")
                setPositiveButton("确认") { dialog, _ ->
                    dialog.dismiss()
                    openBingWebsite()
                }
                setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }
                create().show()
            }
        } catch (e: Exception) {
            Log.e("HomeFragment", "Error showing dialog", e)
        }
    }
    
    private fun openBingWebsite() {
        Log.d("HomeFragment", "Opening Bing website")
        try {
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.bing.com")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }.takeIf { intent ->
                intent.resolveActivity(requireContext().packageManager) != null
            }?.let { startActivity(it) }
            ?: Toast.makeText(requireContext(), "未找到可用浏览器", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("HomeFragment", "Error opening website", e)
            Toast.makeText(requireContext(), "功能暂不可用", Toast.LENGTH_SHORT).show()
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