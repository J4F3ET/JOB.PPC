package com.example.jobcpp.ViewModel.Service

import android.view.MotionEvent
import android.widget.TextView
import com.example.jobcpp.Utils.MovementDirection
import com.example.jobcpp.ViewModel.Interface.InterfaceMoveObject

class EventToMovement:InterfaceMoveObject {
    override fun validateMove(box: Short, board: Collection<Short>): Boolean {
        return false
    }

    override fun executeMovement(
        direction: MovementDirection?,
        board: Collection<Short>
    ): Collection<Short> {
        println("MOvimiento ejecutado hacia $direction")
        return board
    }
}