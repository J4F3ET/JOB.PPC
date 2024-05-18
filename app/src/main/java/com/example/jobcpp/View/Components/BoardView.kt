package com.example.jobcpp.View.Components



import android.content.Context
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.jobcpp.View.Components.Utils.TextViewAdapter

class BoardView(
    context: Context,
    columns: Byte,
    content: List<TextView>
) {
    val grid:GridView;
    private val gap:Int = 4;

    init {
        val dimension = context.resources.displayMetrics.widthPixels.times(.8).toInt()
        val adapterList = TextViewAdapter(context,content)
        this.grid = GridView(context,).apply {
            numColumns = columns.toInt();
            layoutParams = ViewGroup.LayoutParams(dimension, dimension)
            horizontalSpacing = gap;
            verticalSpacing = gap;
            setPadding(5,5,5,5);
            clipToPadding = false
            adapter = adapterList;
            background = ContextCompat.getDrawable(context, com.example.jobcpp.R.drawable.border);
            columnWidth = GridView.AUTO_FIT
        }
    }
}