package com.example.jobcpp.ViewModel.Interface

import com.example.jobcpp.Model.Interface.InterfaceBoard
import com.example.jobcpp.Model.Interface.InterfaceBox
import com.example.jobcpp.Utils.MovementDirection


interface InterfaceMoveObject {
    fun validateMove(box: InterfaceBox, board: InterfaceBoard<InterfaceBox>):Boolean
    fun executeMovement(
        direction: MovementDirection, box: InterfaceBox, board: InterfaceBoard<InterfaceBox>
    ):InterfaceBoard<InterfaceBox>
}