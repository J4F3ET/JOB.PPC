package com.example.jobcpp.View.Components



import android.content.Context
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.jobcpp.Utils.MovementDirection
import com.example.jobcpp.ViewModel.Service.EventToMovement
import com.example.jobcpp.View.Utils.TextViewAdapter



class BoardView(
    context: Context,
    private val columns: Int,
    var content: List<Short>
) {
    val grid:GridView;
    private val gap:Int = 4;
    private var lastX:Float = 0f;
    private var lastY:Float = 0f;
    private val eventToMovement: EventToMovement = EventToMovement()

    init {
        val dimension = context.resources.displayMetrics.widthPixels.times(.8).toInt();
        val padding = 15;
        val items = adapterListValuesToListTextViews(context,this.content)
        val adapterList = TextViewAdapter(context,items);
        this.grid = GridView(context,).apply {
            numColumns = columns;
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
                this.lastY = 0f;
                this.lastX = 0f;
                if(movementDirection is MovementDirection){
                    this.content = eventToMovement.executeMovement(
                        movementDirection,
                        content
                    ).toList()
                }
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
    fun adapterListValuesToListTextViews(context: Context, values:List<Short>):List<TextView>{
        val items: List<TextView> = List(values.size,) {
            ElementBoardView(
                context,
                this.columns,
                values[it].toInt()
            ).textView
        }
        return items
    }

}