package com.example.jobcpp

import android.os.Bundle
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jobcpp.View.Components.BoardView
import com.example.jobcpp.View.Components.ElementBoardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val columns:Byte = 4;
        val items: MutableList<TextView> = mutableListOf()
        for (num in 0..(columns.times(columns))){
            val textView:TextView = ElementBoardView(this).textView
            items.add(textView)
        }
        val boardView = BoardView(this,columns,items);
        val gridView: GridView = boardView.grid;
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val containerLinearLayout:LinearLayout = findViewById(R.id.container_board)
        containerLinearLayout.addView(gridView)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}