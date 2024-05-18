package com.example.jobcpp.View.Components

//noinspection SuspiciousImport
import android.R
import android.content.Context
import android.os.Build
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.core.content.ContextCompat

class BoardView(
    context: Context,
    columns: Byte,
    content: List<TextView>
) {
    val grid:GridView;
    private val gap:Int = 4;

    init {
        val adapterList = ArrayAdapter(context, R.layout.simple_list_item_1, content)
        this.grid = GridView(context,).apply {
            numColumns = columns.toInt();
            horizontalSpacing = gap;
            verticalSpacing = gap;
            adapter = adapterList;
            background = ContextCompat.getDrawable(context, com.example.jobcpp.R.drawable.border)
        }
    }
}