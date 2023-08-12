package com.example.app_32_actors.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_32_actors.repository.ActorRepository
import com.example.app_32_actors.retrofit.Actor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActorViewModel(val repository: ActorRepository) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Actor>())
    val state : StateFlow<List<Actor>>
        get() = _state

    init {
        viewModelScope.launch {
            val actor = repository.getActors()
            _state.value = actor
        }
    }

}