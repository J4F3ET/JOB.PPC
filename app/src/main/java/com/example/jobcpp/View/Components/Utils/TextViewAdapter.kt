package com.example.jobcpp.View.Components.Utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TextViewAdapter(private val context: Context, private val items: List<TextView>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = if (convertView == null) {
            TextView(context).apply {
                layoutParams = items[position].layoutParams
            }
        } else {
            convertView as TextView
        }

        // Configura el TextView según sea necesario
        textView.text = items[position].text
        textView.background = items[position].background
        textView.setTextColor(items[position].currentTextColor)
        textView.gravity = items[position].gravity


        return textView
    }
}