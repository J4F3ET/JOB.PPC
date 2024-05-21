package com.example.jobcpp.ViewModel.Interface

import com.example.jobcpp.Utils.MovementDirection


interface InterfaceMoveObject {
    fun validateMove(box: Short, board: List<Short>):Boolean
    fun executeMovement(
        direction: MovementDirection,
        board: List<Short>
    ):Pair<Number,List<Short>>
}