package com.wohlmuth.onenote2021

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NoteAdapter(var context: Context, var notes: List<String>): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        val title = view.findViewById(R.id.tvTitle) as TextView
        val message = view.findViewById(R.id.tvMessage) as TextView
        title.text = Preferences().getNoteTitle(context)
        message.text = Preferences().getNoteMessage(context)
        return view
    }

    override fun getItem(position: Int): Any {
        return notes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return notes.size
    }
}