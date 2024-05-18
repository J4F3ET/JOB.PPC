package com.example.jobcpp.View.Components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.NotificationCompat.Style
import androidx.core.content.ContextCompat
import com.example.jobcpp.R
import com.example.jobcpp.Utils.StyleElementBoard

class ElementBoardView(
    contex:Context,
    value:Byte=0
){
    val textView:TextView;
    init {
        val backgroundElement: Drawable = ShapeCreator.createBackground(contex,value.toInt())
        val dimension = contex.resources.displayMetrics.widthPixels.times(.75).div(4).toInt()
        this.textView = TextView(contex).apply {
            layoutParams = LinearLayout.LayoutParams(dimension,dimension)
            setTextColor(ContextCompat.getColor(contex,R.color.white))
            gravity = Gravity.CENTER
            background = backgroundElement
            text = value.toString()
        }
    }

    fun createBackground(context: Context, value:Int = 0):Drawable? = when(value){
            0 -> StyleElementBoard.INIT_STYLE.getDrawable(context)
            2 -> StyleElementBoard.BASIC_STYLE.getDrawable(context)
            4 -> StyleElementBoard.MID_STYLE.getDrawable(context)
            8 -> StyleElementBoard.ADVANCE_STYLE.getDrawable(context)
            16 -> StyleElementBoard.FINAL_STYLE.getDrawable(context)
            else -> ContextCompat.getDrawable(context, R.drawable.border)
    }
    fun createBackground(value: Int): Int {
        val baseColor = Color.BLUE
        val adjustedColor = adjustColor(baseColor, value)
        return adjustedColor
    }

    fun createBorder(value: Int): Int {
        val baseColor = Color.BLACK
        val adjustedColor = adjustColor(baseColor, value)
        return adjustedColor
    }
    private fun adjustColor(baseColor: Int, value: Int): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(baseColor, hsv)
        hsv[2] = hsv[2] * (1 - value * 0.1f)
        return Color.HSVToColor(hsv)
    }
}