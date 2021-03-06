package com.wohlmuth.onenote2021

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NoteAdapter(var context: Context, var notes: List<Note>): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private class ViewHolder {
        lateinit var title: TextView
        lateinit var message: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        // Check if the view already exists. If it does, there’s no need to inflate from the layout and call findViewById() again.
        if (convertView == null) {

            // If the view does not exist, you inflate the custom row layout from your XML.
            view = inflater.inflate(R.layout.list_item, parent, false)

            // Create a new ViewHolder with subviews initialized by using findViewById().
            holder = ViewHolder()
            holder.title = view.findViewById(R.id.tvTitle) as TextView
            holder.message = view.findViewById(R.id.tvMessage) as TextView

            // Hang onto this holder for future recycling by using setTag() to set the tag property of the view that the holder belongs to.
            view.tag = holder
        } else {

            // Skip all the expensive inflation steps and just get the holder you already made.
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        // Get relevant subviews of the row view.
        val tvNoteTitle = holder.title
        val tvNoteMessage = holder.message

        // Get our note object for current position using getItem(position).
        val note = getItem(position) as Note

        // Set text on TextViews
        tvNoteTitle.text = note.title
        tvNoteMessage.text = note.message

        // Return view containing all text values for current position
        return view
    }

    override fun getItem(position: Int): Any {
        return notes[position]
    }

    override fun getItemId(position: Int): Long {
        return notes[position].id
    }

    override fun getCount(): Int {
        return notes.size
    }
}