package com.emotional.companionship.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emotional.companionship.data.model.DigitalHuman
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectExistingDigitalHumanViewModel @Inject constructor() : ViewModel() {
    private val _digitalHumans = MutableLiveData<List<DigitalHuman>>()
    val digitalHumans: LiveData<List<DigitalHuman>> = _digitalHumans

    private val _selectedDigitalHuman = MutableLiveData<DigitalHuman?>()
    val selectedDigitalHuman: LiveData<DigitalHuman?> = _selectedDigitalHuman

    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    init {
        loadDigitalHumans()
    }

    private fun loadDigitalHumans() {
        // TODO: Load digital humans from database
        _digitalHumans.value = emptyList()
    }

    fun onDigitalHumanSelected(digitalHuman: DigitalHuman) {
        _selectedDigitalHuman.value = digitalHuman
    }

    fun onConfirmClick() {
        _selectedDigitalHuman.value?.let { digitalHuman ->
            _navigationEvent.value = NavigationEvent.NavigateBack(digitalHuman)
        }
    }

    sealed class NavigationEvent {
        data class NavigateBack(val digitalHuman: DigitalHuman) : NavigationEvent()
    }
} 