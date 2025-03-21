package com.emotional.companionship.ui.select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.emotional.companionship.R
import com.emotional.companionship.databinding.ActivitySelectDigitalHumanBinding
import com.emotional.companionship.ui.create.CreateDigitalHumanActivity
import com.emotional.companionship.data.model.DigitalHuman

class SelectDigitalHumanActivity : AppCompatActivity(), DigitalHumanClickListener {
    private lateinit var binding: ActivitySelectDigitalHumanBinding
    private val viewModel: SelectDigitalHumanViewModel by viewModels()
    private val adapter = DigitalHumanAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_digital_human)
        
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvDigitalHumans.adapter = adapter

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.digitalHumans.observe(this) { digitalHumans ->
            adapter.submitList(digitalHumans)
        }

        viewModel.navigationEvent.observe(this) { event ->
            when (event) {
                is SelectDigitalHumanViewModel.NavigationEvent.NavigateToCreate -> {
                    CreateDigitalHumanActivity.start(this)
                }
                is SelectDigitalHumanViewModel.NavigationEvent.NavigateToChat -> {
                    // TODO: Navigate to chat screen
                }
            }
        }
    }

    override fun onDigitalHumanClick(digitalHuman: DigitalHuman) {
        viewModel.onDigitalHumanClick(digitalHuman)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SelectDigitalHumanActivity::class.java))
        }
    }
} 