package com.wohlmuth.onenote2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText

class NoteEditActivity : AppCompatActivity(), View.OnClickListener {

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

    override fun onClick(view: View?) {
        // Store Title and Message in preferences
        Preferences().setNoteTitle(this, etTitle?.text.toString())
        Preferences().setNoteMessage(this, etMessage?.text.toString())
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)

        return true
    }
}