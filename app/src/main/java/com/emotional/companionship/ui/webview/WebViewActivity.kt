package com.emotional.companionship.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.emotional.companionship.R
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "WebViewActivity"

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnBack: ImageButton
    private lateinit var tvTitle: TextView
    
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        
        initViews()
        setupToolbar()
        setupWebView()
        setupBackPressedCallback()
        
        // 获取传入的URL
        val url = intent.getStringExtra("url") ?: "https://m.bing.com"
        loadUrl(url)
    }
    
    private fun initViews() {
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        btnBack = findViewById(R.id.btnBack)
        tvTitle = findViewById(R.id.tvTitle)
    }
    
    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        
        // 隐藏默认标题
        supportActionBar?.setDisplayShowTitleEnabled(false)
        
        // 设置返回按钮点击事件
        btnBack.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                finish()
            }
        }
    }
    
    // 设置返回事件处理
    private fun setupBackPressedCallback() {
        // 添加返回按钮回调处理，替代onBackPressed
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        try {
            // WebView基本设置
            webView.settings.apply {
                // 启用JavaScript
                javaScriptEnabled = true
                
                // 启用DOM存储API
                domStorageEnabled = true
                
                // 设置缓存模式
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                
                // 设置适应屏幕
                loadWithOverviewMode = true
                useWideViewPort = true
                
                // 禁用缩放
                setSupportZoom(false)
                builtInZoomControls = false
                displayZoomControls = false
                
                // 支持图片自动加载
                loadsImagesAutomatically = true
                
                // 允许混合内容
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                
                // 设置默认编码
                defaultTextEncodingName = "UTF-8"
                
                // 禁用文件访问
                allowFileAccess = false
                allowContentAccess = false
                
                // 禁用数据库
                databaseEnabled = false
                
                // 禁用地理位置
                setGeolocationEnabled(false)
                
                // 设置UA字符串
                userAgentString = userAgentString.replace("wv", "")
            }
            
            // 设置WebViewClient不在外部浏览器中打开链接
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    return false // 返回false表示在WebView中处理URL
                }
                
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // 更新标题
                    view?.title?.let { title ->
                        if (title.isNotEmpty()) {
                            tvTitle.text = title
                        }
                    }
                }
                
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    Log.e(TAG, "WebView Error: ${error?.description}")
                    
                    if (request?.isForMainFrame == true) {
                        // 主框架错误时显示错误信息
                        Toast.makeText(this@WebViewActivity, "加载页面失败，请稍后重试", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            
            // 设置WebChromeClient监听加载进度
            webView.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    progressBar.progress = newProgress
                    
                    if (newProgress == 100) {
                        progressBar.visibility = View.GONE
                    } else {
                        progressBar.visibility = View.VISIBLE
                    }
                }
                
                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    title?.let {
                        if (it.isNotEmpty()) {
                            tvTitle.text = it
                        }
                    }
                }
            }
            
            // 禁用长按菜单
            webView.setOnLongClickListener { true }
            
            // 禁用滚动条
            webView.isVerticalScrollBarEnabled = false
            webView.isHorizontalScrollBarEnabled = false
            
        } catch (e: Exception) {
            Log.e(TAG, "setupWebView error: ${e.message}", e)
        }
    }
    
    private fun loadUrl(url: String) {
        try {
            webView.loadUrl(url)
        } catch (e: Exception) {
            Log.e(TAG, "loadUrl error: ${e.message}", e)
            Toast.makeText(this, "无法加载页面: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onResume() {
        super.onResume()
        // 恢复WebView
        webView.onResume()
    }
    
    override fun onPause() {
        // 暂停WebView
        webView.onPause()
        super.onPause()
    }
    
    override fun onDestroy() {
        // 清除WebView
        webView.apply {
            clearHistory()
            clearCache(true)
            clearFormData()
            clearSslPreferences()
            destroy()
        }
        super.onDestroy()
    }
} 