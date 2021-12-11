package com.wohlmuth.onenote2021

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class NoteEditActivity : AppCompatActivity(), View.OnClickListener, DialogInterface.OnClickListener {

    private var etTitle: EditText? = null
    private var etMessage: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)

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
        finish()
    }

    override fun onClick(view: View?) {
        // Store Title and Message in preferences
        Preferences().setNoteTitle(this, etTitle?.text.toString())
        Preferences().setNoteMessage(this, etMessage?.text.toString())
        finish()
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
        }

        return true
    }

    override fun onClick(p0: DialogInterface?, p1: Int) {
        deleteNote()
    }
}