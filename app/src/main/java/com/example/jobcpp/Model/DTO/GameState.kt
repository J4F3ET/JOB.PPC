package com.example.jobcpp.Model.DTO


import com.example.jobcpp.Model.Interface.InterfaceBoard
import com.example.jobcpp.Model.Interface.InterfaceBox


data class GameState(
    val board: InterfaceBoard<InterfaceBox>,
    var score: Int,
    var best: Int
)
