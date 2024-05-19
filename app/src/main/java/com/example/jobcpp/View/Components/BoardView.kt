package com.example.jobcpp.View.Components



import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.jobcpp.Utils.MovementDirection
import com.example.jobcpp.View.Components.Utils.TextViewAdapter



class BoardView(
    context: Context,
    columns: Byte,
    content: List<TextView>
) {
    val grid:GridView;
    private val gap:Int = 4;
    private var lastX:Float = 0f;
    private var lastY:Float = 0f;

    init {
        val dimension = context.resources.displayMetrics.widthPixels.times(.8).toInt();
        val padding = 15;
        val adapterList = TextViewAdapter(context,content);
        this.grid = GridView(context,).apply {
            numColumns = columns.toInt();
            layoutParams = ViewGroup.LayoutParams(dimension, dimension);
            horizontalSpacing = gap;
            verticalSpacing = gap;
            clipToPadding = false
            adapter = adapterList;
            background = ContextCompat.getDrawable(context, com.example.jobcpp.R.drawable.border);
            columnWidth = GridView.AUTO_FIT;
            setPadding(padding,padding,padding,padding);
            setOnTouchListener { _, event -> eventGrid(event) };
        }
    }
    private fun eventGrid(event: MotionEvent):Boolean{
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                this.lastX = event.x;
                this.lastY = event.y;
                true;
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                val movementDirection:MovementDirection? = resolveOrientationEvent(
                    event.x - this.lastX,
                    event.y - this.lastY
                )
                println(movementDirection)
                this.lastY = 0f;
                this.lastX = 0f;
                true;
            }
            else -> false
        }
    }
    private fun resolveOrientationEvent(deltaX: Float, deltaY: Float): MovementDirection? {
        return when {
            deltaX > 0 && Math.abs(deltaX) > Math.abs(deltaY) -> MovementDirection.RIGHT
            deltaX < 0 && Math.abs(deltaX) > Math.abs(deltaY) -> MovementDirection.LEFT
            deltaY > 0 && Math.abs(deltaY) > Math.abs(deltaX) -> MovementDirection.DOWN
            deltaY < 0 && Math.abs(deltaY) > Math.abs(deltaX) -> MovementDirection.UP
            else -> null
        }
    }

}