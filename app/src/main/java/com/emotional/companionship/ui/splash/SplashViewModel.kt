package com.emotional.companionship.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    fun onStartClick() {
        _navigationEvent.value = NavigationEvent.NavigateToOnboarding
    }

    sealed class NavigationEvent {
        object NavigateToOnboarding : NavigationEvent()
    }
} 