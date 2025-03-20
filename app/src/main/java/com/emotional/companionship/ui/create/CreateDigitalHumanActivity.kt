package com.emotional.companionship.ui.create

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emotional.companionship.R
import com.emotional.companionship.databinding.ActivityCreateDigitalHumanBinding

class CreateDigitalHumanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateDigitalHumanBinding
    private val viewModel: CreateDigitalHumanViewModel by viewModels()

    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { viewModel.onImageSelected(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDigitalHumanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupGenderDropdown()
        setupObservers()
    }

    private fun setupGenderDropdown() {
        val genders = arrayOf(getString(R.string.male), getString(R.string.female))
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genders)
        (binding.tilGender.editText as? android.widget.AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun setupObservers() {
        viewModel.navigationEvent.observe(this) { event ->
            when (event) {
                is CreateDigitalHumanViewModel.NavigationEvent.SelectImage -> {
                    selectImage.launch("image/*")
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