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

class NoteEditActivity : AppCompatActivity(), View.OnClickListener, DialogInterface.OnClickListener {

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
        etTitle?.setText(Preferences().getNoteTitle(this))
        etMessage?.setText(Preferences().getNoteMessage(this))

        // Set OnClickListener
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener(this)
    }

    private fun deleteNote() {
        Preferences().setNoteTitle(this, null)
        Preferences().setNoteMessage(this, null)

        // Display Toast
        Toast.makeText(this, R.string.note_deleted, Toast.LENGTH_LONG).show()

        finish()
    }

    private fun saveNote() {
        val title = etTitle?.text.toString()
        val message = etMessage?.text.toString()
        val db = Database(this)
        db.insertNote(Note(0, System.currentTimeMillis(), title, message))
        db.getAllNotes()

        // Display Toast
        Toast.makeText(this, R.string.note_saved, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onClick(view: View?) {
        saveNote()
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