package com.wohlmuth.onenote2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

class ListActivity : AppCompatActivity(), View.OnClickListener {

    private var tvTitle: TextView? = null
    private var tvMessage: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Find views by id
        tvTitle = findViewById(R.id.tvTitle)
        tvMessage = findViewById(R.id.tvMessage)

        updateNote()
    }

    private fun updateNote() {
        tvTitle?.text = Preferences().getNoteTitle(this)
        tvMessage?.text = Preferences().getNoteMessage(this)
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