package com.emotional.companionship.ui.create

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import java.util.UUID

class CreateDigitalHumanViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val gender = MutableLiveData<String>()
    val personality = MutableLiveData<String>()
    val avatarUri = MutableLiveData<Uri>()

    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    val isFormValid = name.map { name ->
        !name.isNullOrBlank() && !gender.value.isNullOrBlank() && !personality.value.isNullOrBlank() && avatarUri.value != null
    }

    fun onUploadClick() {
        _navigationEvent.value = NavigationEvent.SelectImage
    }

    fun onImageSelected(uri: Uri) {
        avatarUri.value = uri
    }

    fun onNextClick() {
        // TODO: Save digital human data
        _navigationEvent.value = NavigationEvent.NavigateToChat
    }

    sealed class NavigationEvent {
        object SelectImage : NavigationEvent()
        object NavigateToChat : NavigationEvent()
    }
} 