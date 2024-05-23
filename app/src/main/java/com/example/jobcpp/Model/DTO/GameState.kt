package com.example.jobcpp.Model.DTO


import com.example.jobcpp.Model.Interface.InterfaceBoard
import java.util.LinkedList


data class GameState(
    var board: List<Short>,
    var score: Int,
    var best: Int
)
