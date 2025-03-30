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
import androidx.recyclerview.widget.LinearLayoutManager
import com.emotional.companionship.R
import com.emotional.companionship.data.model.DigitalHuman
import com.emotional.companionship.databinding.FragmentHomeBinding
import com.emotional.companionship.ui.create.CreateDigitalHumanActivity
import com.emotional.companionship.util.UserSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "HomeFragment"

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
        Log.d(TAG, "onCreateView: 创建视图")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: 视图已创建")
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        Log.d(TAG, "setupRecyclerView: 设置RecyclerView")
        
        // 确保使用线性布局管理器
        binding.rvDigitalHumans.layoutManager = LinearLayoutManager(requireContext())
        
        // 初始化适配器
        adapter = DigitalHumanAdapter(
            onButtonClick = { digitalHuman ->
                Log.d(TAG, "数字人按钮点击: ${digitalHuman.name}")
                activity?.runOnUiThread {
                    if (isAdded && !isDetached) {
                        showDialog(digitalHuman)
                    }
                }
            },
            onAddClick = {
                Log.d(TAG, "添加数字人卡片点击")
                CreateDigitalHumanActivity.start(requireContext())
            }
        )
        
        // 设置适配器
        binding.rvDigitalHumans.adapter = adapter
        Log.d(TAG, "适配器已设置到RecyclerView")
    }

    private fun observeViewModel() {
        Log.d(TAG, "observeViewModel: 观察ViewModel")
        viewModel.digitalHumans.observe(viewLifecycleOwner) { digitalHumans ->
            Log.d(TAG, "收到数字人列表，数量: ${digitalHumans.size}")
            adapter.submitList(digitalHumans)
        }
    }
    
    private fun showDialog(digitalHuman: DigitalHuman) {
        Log.d(TAG, "showDialog: 显示对话框，数字人: ${digitalHuman.name}")
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
            Log.e(TAG, "显示对话框出错", e)
        }
    }
    
    private fun openBingWebsite() {
        Log.d(TAG, "openBingWebsite: 打开Bing网站")
        try {
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.bing.com")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }.takeIf { intent ->
                intent.resolveActivity(requireContext().packageManager) != null
            }?.let { startActivity(it) }
            ?: Toast.makeText(requireContext(), "未找到可用浏览器", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e(TAG, "打开网站出错", e)
            Toast.makeText(requireContext(), "功能暂不可用", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: 销毁视图")
        _binding = null
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
} 