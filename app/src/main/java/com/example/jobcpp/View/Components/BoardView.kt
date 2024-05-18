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


@SuppressLint("ClickableViewAccessibility")
class BoardView(
    context: Context,
    columns: Byte,
    content: List<TextView>
) {
    val grid:GridView;
    private val gap:Int = 4;

    init {
        val dimension = context.resources.displayMetrics.widthPixels.times(.8).toInt()
        val padding = 15
        var lastX = 0f
        var lastY = 0f
        var deltaX = 0f
        var deltaY = 0f
        val adapterList = TextViewAdapter(context,content)
        this.grid = GridView(context,).apply {
            numColumns = columns.toInt();
            layoutParams = ViewGroup.LayoutParams(dimension, dimension)
            horizontalSpacing = gap;
            verticalSpacing = gap;
            setPadding(padding,padding,padding,padding);
            clipToPadding = false
            adapter = adapterList;
            background = ContextCompat.getDrawable(context, com.example.jobcpp.R.drawable.border);
            columnWidth = GridView.AUTO_FIT
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Guardar las coordenadas iniciales cuando el toque comienza
                        lastX = event.x
                        lastY = event.y
                        true
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        // Resetear las coordenadas al finalizar el toque
                        val movementDirection:MovementDirection = resolveOrientationEvent(
                            event.x - lastX,
                            event.y - lastY
                        )
                        lastX = 0f
                        lastY = 0f
                        true
                    }
                    else -> false
                }
            }
        }
    }
    fun resolveOrientationEvent(deltaX: Float, deltaY: Float): MovementDirection {
        return when {
            deltaX > 0 && Math.abs(deltaX) > Math.abs(deltaY) -> MovementDirection.RIGHT
            deltaX < 0 && Math.abs(deltaX) > Math.abs(deltaY) -> MovementDirection.LEFT
            deltaY > 0 && Math.abs(deltaY) > Math.abs(deltaX) -> MovementDirection.DOWN
            deltaY < 0 && Math.abs(deltaY) > Math.abs(deltaX) -> MovementDirection.UP
            else -> throw IllegalArgumentException("No movement detected")
        }
    }

}