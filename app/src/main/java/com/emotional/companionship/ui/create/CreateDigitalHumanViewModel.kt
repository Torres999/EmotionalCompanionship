package com.emotional.companionship.ui.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emotional.companionship.data.model.DigitalHuman
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CreateDigitalHumanViewModel @Inject constructor() : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _relation = MutableLiveData<String>()
    val relation: LiveData<String> = _relation

    private val _personality = MutableLiveData<String>()
    val personality: LiveData<String> = _personality

    private val _navigationEvent = MutableLiveData<NavigationEvent>()
    val navigationEvent: LiveData<NavigationEvent> = _navigationEvent

    fun onNameChanged(name: String) {
        _name.value = name
    }

    fun onRelationChanged(relation: String) {
        _relation.value = relation
    }

    fun onPersonalityChanged(personality: String) {
        _personality.value = personality
    }

    fun onCreateClick() {
        val name = _name.value
        val relation = _relation.value
        val personality = _personality.value

        if (name.isNullOrEmpty() || relation.isNullOrEmpty() || personality.isNullOrEmpty()) {
            _navigationEvent.value = NavigationEvent.ShowError("请填写所有必填项")
            return
        }

        val digitalHuman = DigitalHuman(
            id = System.currentTimeMillis().toString(),
            name = name,
            relation = relation,
            personality = personality,
            avatarUrl = "",
            lastChatTime = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
        )

        _navigationEvent.value = NavigationEvent.NavigateBack(digitalHuman)
    }

    sealed class NavigationEvent {
        data class NavigateBack(val digitalHuman: DigitalHuman) : NavigationEvent()
        data class ShowError(val message: String) : NavigationEvent()
    }
} 