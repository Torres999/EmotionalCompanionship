package com.emotional.companionship.ui.create

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.emotional.companionship.data.model.DigitalHuman
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateDigitalHumanViewModel @Inject constructor() : ViewModel(), Observable {
    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    val name = MutableLiveData<String>()
    val relation = MutableLiveData<String>()
    val personality = MutableLiveData<String>()
    val avatarUri = MutableLiveData<String>()

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean> = _isFormValid

    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    @Bindable
    var isFormValidForBinding: Boolean = false
        private set

    fun getFormValidForBinding(): Boolean {
        return isFormValidForBinding
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    fun onSelectExistingClick() {
        _navigationEvent.value = NavigationEvent.NavigateToSelectExisting
    }

    fun onUploadClick() {
        _navigationEvent.value = NavigationEvent.NavigateToUpload
    }

    fun onMediaSelected(uri: Uri) {
        avatarUri.value = uri.toString()
    }

    fun onExistingDigitalHumanSelected(digitalHuman: DigitalHuman) {
        name.value = digitalHuman.name
        relation.value = digitalHuman.relation
        personality.value = digitalHuman.personality
        // TODO: Convert avatarUrl to Uri
    }

    fun onConfirmClick() {
        // TODO: Save digital human data
        _navigationEvent.value = NavigationEvent.NavigateToChat
    }

    sealed class NavigationEvent {
        object NavigateToSelectExisting : NavigationEvent()
        object NavigateToUpload : NavigationEvent()
        object NavigateToChat : NavigationEvent()
    }
} 