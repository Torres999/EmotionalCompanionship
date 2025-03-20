package com.emotional.companionship.ui.select

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emotional.companionship.data.model.DigitalHuman

class SelectDigitalHumanViewModel : ViewModel() {
    private val _digitalHumans = MutableLiveData<List<DigitalHuman>>()
    val digitalHumans: LiveData<List<DigitalHuman>> = _digitalHumans

    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    init {
        loadDigitalHumans()
    }

    private fun loadDigitalHumans() {
        // TODO: Load digital humans from database
        _digitalHumans.value = emptyList()
    }

    fun onAddClick() {
        _navigationEvent.value = NavigationEvent.NavigateToCreate
    }

    fun onDigitalHumanClick(digitalHuman: DigitalHuman) {
        _navigationEvent.value = NavigationEvent.NavigateToChat(digitalHuman)
    }

    sealed class NavigationEvent {
        object NavigateToCreate : NavigationEvent()
        data class NavigateToChat(val digitalHuman: DigitalHuman) : NavigationEvent()
    }
} 