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
}