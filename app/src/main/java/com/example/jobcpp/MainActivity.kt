package com.example.jobcpp
import android.os.Bundle
import android.widget.GridView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jobcpp.ViewModel.Service.GeneratorGameByFunctions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val gridView: GridView = GeneratorGameByFunctions(4).generatorBoard(this);
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