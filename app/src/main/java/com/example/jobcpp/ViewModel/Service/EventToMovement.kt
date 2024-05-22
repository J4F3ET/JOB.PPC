package com.example.jobcpp.ViewModel.Service

import android.view.MotionEvent
import com.example.jobcpp.Model.DTO.GameState
import com.example.jobcpp.Utils.MovementDirection
import com.example.jobcpp.ViewModel.Exceptions.ErrorExceptionFinallyGame
import com.example.jobcpp.ViewModel.Interface.InterfaceMoveObject
import java.util.LinkedList
import kotlin.math.sqrt

class EventToMovement:InterfaceMoveObject {
    private var lastX:Float = 0f;
    private var lastY:Float = 0f;
    private val calculateToArea: (Number) -> Int = { size -> sqrt(size.toDouble()).toInt() }
    private val calculateIndexRow:(area:Int,row:Int) -> Int = {area,row-> area.times(row-1)}
    private val calculateIndexCol:(area:Int,row:Int,col:Int) -> Int = {area,row,col-> area.times(row-1)+(col-1)}
    override fun validateMove(box: Short, board: List<Short>): Boolean {
        return false
    }
    override fun executeMovement(
        direction: MovementDirection,
        score: Int,
        board: List<Short>
    ): Pair<Number,List<Short>>{
        val area:Int = calculateToArea(board.size)
        var resultMovement:Pair<Number, LinkedList<Short>> = Pair(score,board as LinkedList)
        if(direction == MovementDirection.RIGHT || direction == MovementDirection.LEFT){
            resultMovement = horizontalMovement(direction,score,board,area)
        }
        val newBoard = generatorRandomValue(resultMovement.second)
        println(newBoard)
        return Pair(resultMovement.first,newBoard)
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
                        val result:Pair<Number,List<Short>> = this.executeMovement(
                            movementDirection,
                            gameState.score,
                            gameState.board
                        )
                        gameState.board =  result.second
                        gameState.score += result.first.toInt()
                        gameState.best = if(gameState.score>gameState.best)
                            gameState.score
                        else
                            gameState.best
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
    private fun horizontalMovement(
        direction: MovementDirection,
        scoreSum:Int,
        board: List<Short>,
        area:Int = calculateToArea(board.size)
    ):Pair<Number,LinkedList<Short>> {
        var newScoreSum = scoreSum;
        var newBoardShort: MutableList<Short> = board.toMutableList()
        for (row in 1..area){
            val resultRow:Pair<List<Short>,List<Int>> = getRowToBoard(row,board,area)
            val rowValues: MutableList<Int> = resultRow.first.map { it.toInt() }.toMutableList()
            if (direction == MovementDirection.RIGHT){
                rowValues.reverse()
            }
            val result:Pair<Int, MutableList<Int>> = shift(rowValues)
            val newElement = if (direction == MovementDirection.RIGHT){
                result.second.reverse()
                result.second
            }else
                result.second
            newBoardShort = replaceElement(newBoardShort,newElement,resultRow.second)
            newScoreSum += result.first
        }
        return Pair(newScoreSum,LinkedList(newBoardShort))
    }
    private fun shift(
        element:MutableList<Int>,
    ):Pair<Int,MutableList<Int>>{
        var i = 0
        var score = 0
        val size = element.size;
        while (i < size){
            var j = i + 1
            while (j < size){
                if (element[i]!=0 && element[i] == element[j]){
                    element[i] += element[j]
                    score += element[i]
                    element[j] = 0
                    i++
                }else if(element[i] == 0 && element[j] != 0){
                    element[i] = element[j]
                    element[j] = 0
                }
                j++
            }
            i++
        }
        return  Pair(score,element)
    }
    private fun replaceElement(board: MutableList<Short>,element: MutableList<Int>,indices:List<Int>):MutableList<Short>{
        var i = 0
        while (i<element.size){
            board[indices[i]] = element[i].toShort()
            i++
        }
        return board
    }
//    private fun verticalMovement(
//        direction: MovementDirection,
//        board: List<Short>
//    ):Pair<Number,List<Short>>{
//
//    }
    private fun getColumnBoard(
        colum:Int,
        board: List<Short>,
        area:Int = calculateToArea(board.size)
    ): Pair<List<Short>,List<Int>>{
        val col:LinkedList<Short> = LinkedList()
        val indexsCol: LinkedList<Int> = LinkedList()
        for(i in 1..area){
            val indexElementInCol = calculateIndexCol(area,i,colum)
            val valueBox = board[indexElementInCol]
            indexsCol.add(indexElementInCol)
            col.add(valueBox)
        }
        return Pair(col,indexsCol)
    }
    private fun getRowToBoard(
        row:Int,
        board: List<Short>,
        area:Int = calculateToArea(board.size)
    ): Pair<List<Short>,MutableList<Int>>{
        val indexInit = calculateIndexRow(area,row)
        val values = board.subList(indexInit,indexInit+area)
        val index = (indexInit..<indexInit + area).toMutableList()
        return Pair(values,index)
    }
}