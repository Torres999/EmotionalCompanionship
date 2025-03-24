package com.emotional.companionship.ui.create

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.emotional.companionship.R
import com.emotional.companionship.data.model.DigitalHuman
import com.emotional.companionship.databinding.ActivitySelectExistingDigitalHumanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectExistingDigitalHumanActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectExistingDigitalHumanBinding
    private val viewModel: SelectExistingDigitalHumanViewModel by viewModels()
    private val adapter = SelectExistingDigitalHumanAdapter { digitalHuman ->
        viewModel.onDigitalHumanSelected(digitalHuman)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectExistingDigitalHumanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.rvDigitalHumans.adapter = adapter
        binding.btnConfirm.setOnClickListener {
            viewModel.onConfirmClick()
        }
    }

    private fun observeViewModel() {
        viewModel.digitalHumans.observe(this) { digitalHumans ->
            adapter.submitList(digitalHumans)
        }

        viewModel.selectedDigitalHuman.observe(this) { digitalHuman ->
            binding.btnConfirm.isEnabled = digitalHuman != null
        }

        viewModel.navigationEvent.observe(this) { event ->
            when (event) {
                is SelectExistingDigitalHumanViewModel.NavigationEvent.NavigateBack -> {
                    val intent = Intent().apply {
                        putExtra(EXTRA_SELECTED_DIGITAL_HUMAN, event.digitalHuman)
                    }
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    companion object {
        private const val EXTRA_SELECTED_DIGITAL_HUMAN = "extra_selected_digital_human"

        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, SelectExistingDigitalHumanActivity::class.java))
        }

        fun getSelectedDigitalHuman(data: Intent): DigitalHuman? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                data.getParcelableExtra(EXTRA_SELECTED_DIGITAL_HUMAN, DigitalHuman::class.java)
            } else {
                @Suppress("DEPRECATION")
                data.getParcelableExtra(EXTRA_SELECTED_DIGITAL_HUMAN)
            }
        }
    }
} 