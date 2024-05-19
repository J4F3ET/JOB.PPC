package com.example.jobcpp

import android.os.Bundle
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jobcpp.View.Components.BoardView
import com.example.jobcpp.View.Components.ElementBoardView

class MainActivity : AppCompatActivity() {
    fun getRandomPowerOfTwo(): Int {
        // Lista predefinida de potencias de 2 desde 2 hasta 2048
        val powersOfTwo = listOf(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048)

        // Seleccionar aleatoriamente un valor de la lista
        return powersOfTwo.random()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val columns:Byte = 4;
        //------------ HARD CODE -------------------
        val items: MutableList<TextView> = mutableListOf()
        for (num in 1..(columns.times(columns))){
            val textView:TextView = ElementBoardView(this,columns.toShort(),getRandomPowerOfTwo()).textView
            items.add(textView)
        }
        //--------------------------------------------
        // Crea el grid he infla el grid
        val boardView = BoardView(this,columns,items);
        val gridView: GridView = boardView.grid;
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Contenedor del grid
        val containerLinearLayout:LinearLayout = findViewById(R.id.container_board)
        containerLinearLayout.addView(gridView)//Agrega el grid al contenedor
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}