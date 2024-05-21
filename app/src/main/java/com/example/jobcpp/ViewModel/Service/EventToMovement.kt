package com.example.jobcpp.ViewModel.Service

import android.view.MotionEvent
import android.widget.TextView
import androidx.lifecycle.LiveData
import com.example.jobcpp.Model.DTO.GameState
import com.example.jobcpp.Utils.MovementDirection
import com.example.jobcpp.ViewModel.Exceptions.ErrorExceptionFinallyGame
import com.example.jobcpp.ViewModel.Interface.InterfaceMoveObject
import com.example.jobcpp.ViewModel.MainViewModel
import java.util.LinkedList

class EventToMovement:InterfaceMoveObject {
    private var lastX:Float = 0f;
    private var lastY:Float = 0f;
    override fun validateMove(box: Short, board: List<Short>): Boolean {
        return false
    }
    override fun executeMovement(
        direction: MovementDirection,
        board: List<Short>
    ): Pair<Number,List<Short>>{
        val newBoard = generatorRandomValue(board as LinkedList)
        println("MOvimiento ejecutado hacia $direction")
        return Pair(10,newBoard)
    }
    private fun verifyBoxEmptyInBoard(board: LinkedList<Short>):Boolean{
        val element = 0.toShort()
        return board.find { it == element } is Short
    }
    private fun generatorRandomValue(board: LinkedList<Short>):LinkedList<Short>{
        if (!verifyBoxEmptyInBoard(board))
            throw ErrorExceptionFinallyGame("The board is full")
        val range = board.indices
        val valueRandom:Short = listOf(2,4).random().toShort()
        var  i = true
        while (i){
            val index = range.random()
            if(board[index] == 0.toShort()){
                board[index] =  valueRandom
                i = false
            }
        }
        return board
    }
    fun eventMove(event: MotionEvent,gameState: GameState):GameState{
         when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                this.lastX = event.x;
                this.lastY = event.y;
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                val movementDirection:MovementDirection? = resolveOrientationEvent(
                    event.x - this.lastX,
                    event.y - this.lastY
                )
                this.lastY = 0f;
                this.lastX = 0f;
                if(movementDirection is MovementDirection){
                    try {
                        gameState.best++
                        val result:Pair<Number,List<Short>> = this.executeMovement(movementDirection,gameState.board)
                        gameState.board =  result.second
                        gameState.score += result.first.toInt()
                    }catch (e:ErrorExceptionFinallyGame){
                        println("Tira error ${e.message}")
                    }
                }
            }
        }
        return gameState
    }
    private fun resolveOrientationEvent(deltaX: Float, deltaY: Float): MovementDirection? {
        return when {
            deltaX > 0 && Math.abs(deltaX) > Math.abs(deltaY) -> MovementDirection.RIGHT
            deltaX < 0 && Math.abs(deltaX) > Math.abs(deltaY) -> MovementDirection.LEFT
            deltaY > 0 && Math.abs(deltaY) > Math.abs(deltaX) -> MovementDirection.DOWN
            deltaY < 0 && Math.abs(deltaY) > Math.abs(deltaX) -> MovementDirection.UP
            else -> null
        }
    }
}