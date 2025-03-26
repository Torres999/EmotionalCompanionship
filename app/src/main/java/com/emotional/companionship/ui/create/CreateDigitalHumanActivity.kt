package com.emotional.companionship.ui.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emotional.companionship.databinding.ActivityCreateDigitalHumanBinding
import com.emotional.companionship.ui.create.CreateDigitalHumanViewModel.NavigationEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateDigitalHumanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateDigitalHumanBinding
    private val viewModel: CreateDigitalHumanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDigitalHumanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.onNameChanged(s?.toString() ?: "")
            }
        })

        binding.etRelation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.onRelationChanged(s?.toString() ?: "")
            }
        })

        binding.etPersonality.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.onPersonalityChanged(s?.toString() ?: "")
            }
        })

        binding.btnConfirm.setOnClickListener {
            viewModel.onCreateClick()
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.navigationEvent.observe(this) { event ->
            when (event) {
                is NavigationEvent.NavigateBack -> {
                    setResult(RESULT_OK)
                    finish()
                }
                is NavigationEvent.ShowError -> {
                    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
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