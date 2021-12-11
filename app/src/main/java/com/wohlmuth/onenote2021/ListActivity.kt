package com.wohlmuth.onenote2021

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Find view by id
        val listView = findViewById<ListView>(R.id.lvNotes)

        // Create list of strings
        val notes = listOf(
            "Pizza",
            "Döner",
            "Cars",
            "Guns",
            "Girls",
            "Brokkoli",
            "Pizza",
            "Döner",
            "Cars",
            "Guns",
            "Girls",
            "Brokkoli",
            "Pizza",
            "Döner",
            "Cars",
            "Guns",
            "Girls",
            "Brokkoli",
            "Eis"
        )

        // Init adapter
        val noteAdapter = NoteAdapter(this, notes)

        // Set adapter on listView
        listView.adapter = noteAdapter
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun updateView() {
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
}