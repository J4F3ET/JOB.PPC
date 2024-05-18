package com.example.jobcpp.View.Components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import com.example.jobcpp.R

object ShapeCreator {
    fun createBackground(context: Context, value: Int): Drawable {
        val baseColor = ContextCompat.getColor(context, R.color.blue_300)
        val adjustedColor = adjustColor(baseColor, value)
        val shape = GradientDrawable()
        shape.setColor(adjustedColor)
        shape.setStroke(2, Color.WHITE)
        shape.cornerRadius = 8f
        return shape
    }
    private fun adjustColor(baseColor: Int, value: Int): Int {
        val hsv = FloatArray(3)
        android.graphics.Color.colorToHSV(baseColor, hsv)
        hsv[2] = hsv[2] * (1 - value * 0.1f)
        return android.graphics.Color.HSVToColor(hsv)
    }
}