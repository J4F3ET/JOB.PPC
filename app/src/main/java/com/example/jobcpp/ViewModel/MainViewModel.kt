package com.example.jobcpp.ViewModel

import android.annotation.SuppressLint
import android.widget.GridView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobcpp.Model.DTO.GameState
import com.example.jobcpp.Model.DTO.State
import com.example.jobcpp.ViewModel.Service.EventToMovement
import com.example.jobcpp.ViewModel.Service.GeneratorGameByFunctions

@SuppressLint("ClickableViewAccessibility")
class MainViewModel:ViewModel() {
    private val _observableGameState: MutableLiveData<GameState> = MutableLiveData()

    val observableGameState: LiveData<GameState> get() = _observableGameState


    val gridView:GeneratorGameByFunctions = GeneratorGameByFunctions(4);
    init {
        _observableGameState.value = gridView.gameState
    }
    fun updateGameState(newGameState: GameState){
        _observableGameState.value = newGameState

    }
    override fun onCleared() {
        super.onCleared()
    }
}