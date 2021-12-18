package com.wohlmuth.onenote2021

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get

class NoteEditActivity : AppCompatActivity(), View.OnClickListener, DialogInterface.OnClickListener {

    private val db = Database(this)
    private var note: Note? = null
    private var etTitle: EditText? = null
    private var etMessage: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)

        // Display home button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Find views by id
        etTitle = findViewById(R.id.etTitle)
        etMessage = findViewById(R.id.etMessage)

        // Set Title and Message on EditText objects
        val id = intent.getLongExtra("id", -1)
        if (id >= 0) {
            note = db.getNote(id)
            etTitle?.setText(note?.title)
            etMessage?.setText(note?.message)
        }

        // Set OnClickListener
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener(this)
    }

    private fun deleteNote() {
        db.deleteNote(note!!)

        // Display Toast
        Toast.makeText(this, R.string.note_deleted, Toast.LENGTH_LONG).show()

        finish()
    }

    private fun saveNote() {
        val title = etTitle?.text.toString()
        val message = etMessage?.text.toString()
        val db = Database(this)
        if (note == null) {
            // TODO add latitude and longitude
            db.insertNote(Note(0, System.currentTimeMillis(), title, message, 1.1, 1.1))
        } else {
            note?.message = message
            note?.title = title
            db.updateNote(note!!)
        }

        // Display Toast
        Toast.makeText(this, R.string.note_saved, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onClick(view: View?) {
        saveNote()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.get(0)?.isVisible = note != null
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_note, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete) {
            AlertDialog.Builder(this)
                .setMessage(R.string.delete_message)
                .setPositiveButton(R.string.yes, this)
                .setNegativeButton(R.string.no, null)
                .show()
        } else if (item.itemId == android.R.id.home) {
            finish()
        }

        return true
    }

    override fun onClick(p0: DialogInterface?, p1: Int) {
        deleteNote()
    }
}