package com.example.jobcpp.Utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.jobcpp.R

enum class StyleElementBoard(private val colorResId: Int) {
    INIT_STYLE(R.drawable.border_brown),
    BASIC_STYLE(R.drawable.border_brown_dark),
    MID_STYLE(R.drawable.border_blue_300),
    ADVANCE_STYLE(R.drawable.border_blue_400),
    FINAL_STYLE(R.drawable.border_blue_500);

    fun getDrawable(context: Context): Drawable? {
        return ContextCompat.getDrawable(context, colorResId)
    }
}