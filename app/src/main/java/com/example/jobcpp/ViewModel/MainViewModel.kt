package com.example.jobcpp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobcpp.Model.DTO.GameState

class MainViewModel:ViewModel() {
    val observableGameState:MutableLiveData<GameState> = MutableLiveData()

    fun eventMovement(){
        observableGameState.value?.best = observableGameState.value?.best!! + 1
    }
    override fun onCleared() {
        super.onCleared()
    }
}