package com.example.jobcpp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
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
        val textView:TextView = ElementBoardView(this,0).textView
        for (num in 1..(columns.times(columns))){
            items.add(textView)
        }
        val boardView = BoardView(this,columns,items);
        val gridView: GridView = boardView.grid;
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textScore: TextView = findViewById(R.id.input_text_score)
        textScore.text = 0.toString()
        val textBest: TextView = findViewById(R.id.input_text_best)
        textBest.text =0.toString()
        val btn_newGame:Button = findViewById(R.id.btn_newGame)
        btn_newGame.background = ContextCompat.getDrawable(this,R.drawable.border);
        btn_newGame.setTextColor(Color.BLACK)
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