package com.emotional.companionship.ui.create

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emotional.companionship.databinding.ActivityCreateDigitalHumanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateDigitalHumanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateDigitalHumanBinding
    private val viewModel: CreateDigitalHumanViewModel by viewModels()

    private val selectMedia = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { viewModel.onMediaSelected(it) }
    }

    private val selectExisting = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                SelectExistingDigitalHumanActivity.getSelectedDigitalHuman(data)?.let { digitalHuman ->
                    viewModel.onExistingDigitalHumanSelected(digitalHuman)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDigitalHumanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        
        binding.cardSelectExisting.setOnClickListener {
            viewModel.onSelectExistingClick()
        }
        binding.cardUpload.setOnClickListener {
            viewModel.onUploadClick()
        }
    }

    private fun setupObservers() {
        viewModel.navigationEvent.observe(this) { event ->
            when (event) {
                is CreateDigitalHumanViewModel.NavigationEvent.NavigateToSelectExisting -> {
                    SelectExistingDigitalHumanActivity.start(this)
                }
                is CreateDigitalHumanViewModel.NavigationEvent.NavigateToUpload -> {
                    selectMedia.launch("*/*")
                }
                is CreateDigitalHumanViewModel.NavigationEvent.NavigateToChat -> {
                    // TODO: Navigate to chat screen
                    finish()
                }
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CreateDigitalHumanActivity::class.java))
        }
    }
} 