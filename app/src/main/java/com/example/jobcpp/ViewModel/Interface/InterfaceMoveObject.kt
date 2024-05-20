package com.example.jobcpp.ViewModel.Interface

import android.widget.TextView
import com.example.jobcpp.Model.Interface.InterfaceBoard
import com.example.jobcpp.Utils.MovementDirection
import java.util.LinkedList


interface InterfaceMoveObject {
    fun validateMove(box: Short, board: Collection<Short>):Boolean
    fun executeMovement(
        direction: MovementDirection?, board: Collection<Short>
    ):Collection<Short>
}