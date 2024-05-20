package com.example.jobcpp.ViewModel.Service

import android.content.Context
import android.widget.GridView
import com.example.jobcpp.Model.DTO.GameState
import com.example.jobcpp.View.Components.BoardView
import com.example.jobcpp.ViewModel.Interface.InterfaceGeneratorGame
import java.util.LinkedList

class GeneratorGameByFunctions(
    override val columns: Int,
    best:Int = 0,
) : InterfaceGeneratorGame
{
    val gameState: GameState;
    lateinit var boardView: BoardView
    init {
        val board =this.generatorInitialValues(this.columns.times(this.columns))
        this.gameState = GameState(
            board = LinkedList(board),
            score = 0,
            best = best
        )

    }
    override fun generatorBoard(context: Context): GridView {
        boardView = BoardView(
            context,
            this.columns,
            gameState.board
        )
        return boardView.grid
    }
    private fun generatorInitialValues(size:Int):Collection<Short>{
        val range:IntRange = 0..<size
        val index1:Int = range.random()
        var index2:Int
        do {
            index2 = range.random()
        }while (index1 == index2)
        val items: List<Short> = List(size,) {
            if (it == index1 || it == index2) 2 else 0
        }
        return items
    }

}