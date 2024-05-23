package com.example.jobcpp.ViewModel.Interface

import android.content.Context
import android.widget.GridView
import android.widget.TextView
import com.example.jobcpp.Model.DTO.GameState

interface InterfaceGeneratorGame{
    val columns: Int
    fun generatorBoard(context: Context):GridView

}