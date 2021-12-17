package com.wohlmuth.onenote2021

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemClickListener {

    var noteAdapter: BaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Find view by id
        val listView = findViewById<ListView>(R.id.lvNotes)

        // Create list of notes
        val notes: MutableList<Note> = ArrayList()
        notes.add(Note(1, System.currentTimeMillis(),"Title", "Message"))
        notes.add(Note(2, System.currentTimeMillis(),"Title1", "Message1"))
        notes.add(Note(3, System.currentTimeMillis(),"Title2", "Message2"))
        notes.add(Note(1, System.currentTimeMillis(),"Title", "Message"))
        notes.add(Note(2, System.currentTimeMillis(),"Title1", "Message1"))
        notes.add(Note(3, System.currentTimeMillis(),"Title2", "Message2"))
        notes.add(Note(1, System.currentTimeMillis(),"Title", "Message"))
        notes.add(Note(2, System.currentTimeMillis(),"Title1", "Message1"))
        notes.add(Note(3, System.currentTimeMillis(),"Title2", "Message2"))
        notes.add(Note(1, System.currentTimeMillis(),"Title", "Message"))
        notes.add(Note(2, System.currentTimeMillis(),"Title1", "Message1"))
        notes.add(Note(3, System.currentTimeMillis(),"Title2", "Message2"))

        // Init adapter
        noteAdapter = NoteAdapter(this, notes)

        // Set adapter on listView
        listView.adapter = noteAdapter
        listView.onItemClickListener = this
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun updateView() {
        noteAdapter?.notifyDataSetChanged()
    }

    override fun onClick(view: View?) {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add) {
            val intent = Intent(this, NoteEditActivity::class.java)
            startActivity(intent)
        }

        return true
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val intent = Intent(this, NoteEditActivity::class.java)
        startActivity(intent)
    }
}