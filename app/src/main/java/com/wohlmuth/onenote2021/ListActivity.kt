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

    var noteAdapter: NoteAdapter? = null
    var db = Database(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Find view by id
        val listView = findViewById<ListView>(R.id.lvNotes)

        // Init adapter
        noteAdapter = NoteAdapter(this, db.getAllNotes())

        // Set adapter on listView
        listView.adapter = noteAdapter
        listView.onItemClickListener = this
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun updateView() {
        // Create list of notes
        noteAdapter?.notes = db.getAllNotes()
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

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, id: Long) {
        val intent = Intent(this, NoteEditActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}