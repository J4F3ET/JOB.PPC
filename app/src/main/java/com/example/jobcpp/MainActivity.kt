package com.example.jobcpp
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
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
import com.example.jobcpp.Model.DTO.State
import com.example.jobcpp.ViewModel.MainViewModel
import com.example.jobcpp.ViewModel.Service.DatabaseGame
import com.example.jobcpp.ViewModel.Service.EventToMovement

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private val eventToMovement: EventToMovement = EventToMovement()
    private val database= DatabaseGame()
    //Metodo que se ejecuta al tocar el grid
    private fun eventGrid(event:MotionEvent):Boolean{
        var currentGameState: GameState = mainViewModel.observableGameState.value
            ?: return false
        currentGameState = eventToMovement.eventMove(
            event,
            currentGameState,
            this,
            ::eventNewGame
        )
        mainViewModel.updateGameState(currentGameState)
        return true
    }
    private fun eventNewGame():Boolean{
        val currentGameState: GameState = mainViewModel.observableGameState.value
            ?: return false

        currentGameState.board = mainViewModel.gridView.generatorInitialValues(
            currentGameState.
            board.
            size
        )
        currentGameState.score = 0
        mainViewModel.updateGameState(currentGameState)
        return true
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val containerLinearLayout:LinearLayout = findViewById(R.id.container_board)
        val buttonNewGame:Button = findViewById(R.id.btn_newGame)
        val bestText:TextView = findViewById(R.id.input_text_best)
        val scoreText:TextView = findViewById(R.id.input_text_score)
        //GeneratorBoard genera un nuevo juego en
        var gridView:GridView = mainViewModel.gridView.generatorBoard(this)

        gridView.setOnTouchListener{_, event -> eventGrid(event)};
        containerLinearLayout.addView(gridView)

        buttonNewGame.setOnClickListener{
            eventNewGame()
        }
        //Observer que detecta cambios que se efectuaron en el objeto gameState
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

        val btn= findViewById<Button>(R.id.btnState)

        btn.setOnClickListener {
            var board= mainViewModel.observableGameState.value!!.board
            var boardInt= board.map { it.toInt() }
            var score= mainViewModel.observableGameState.value!!.score
            var best= mainViewModel.observableGameState.value!!.best
            var gameState= State(boardInt, score,best)
            database.saveGameState(gameState)
            Log.e("ESATDO DEL JUEGO", board.get(0).toString())
        }
    }
}