package com.example.jobcpp
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jobcpp.Model.DTO.GameState
import com.example.jobcpp.ViewModel.MainViewModel
import com.example.jobcpp.ViewModel.Service.EventToMovement
import com.example.jobcpp.ViewModel.Service.GeneratorGameByFunctions

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private val eventToMovement: EventToMovement = EventToMovement()

    private fun eventGrid(event:MotionEvent):Boolean{
        var currentGameState: GameState = mainViewModel.observableGameState.value
            ?: return false
        currentGameState = eventToMovement.eventMove(event, currentGameState)
        mainViewModel.updateGameState(currentGameState)
        return true
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val containerLinearLayout:LinearLayout = findViewById(R.id.container_board)
        val bestText:TextView = findViewById(R.id.input_text_best)
        val scoreText:TextView = findViewById(R.id.input_text_score)
        var gridView:GridView = mainViewModel.gridView.generatorBoard(this)
        gridView.setOnTouchListener{_, event -> eventGrid(event)};
        containerLinearLayout.addView(gridView)
        mainViewModel
            .observableGameState
            .observe(this, Observer {
                mainViewModel.gridView.gameState = it
                gridView = mainViewModel.gridView.updateBoard(this)
                bestText.text = it.best.toString()
                scoreText.text = it.score.toString()
            })
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}