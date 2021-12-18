package com.wohlmuth.onenote2021

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.view.get
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class NoteEditActivity : AppCompatActivity(), View.OnClickListener, DialogInterface.OnClickListener {

    private val db = Database(this)
    private var note: Note? = null
    private var etTitle: EditText? = null
    private var etMessage: EditText? = null

    private var lastLocation: Location? = null
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_edit)

        // Display home button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Find views by id
        etTitle = findViewById(R.id.etTitle)
        etMessage = findViewById(R.id.etMessage)
        val tvLocation: TextView = findViewById(R.id.tvLocation)

        // Set Title and Message on EditText objects
        val id = intent.getLongExtra("id", -1)
        if (id >= 0) {
            note = db.getNote(id)
            etTitle?.setText(note?.title)
            etMessage?.setText(note?.message)

            if (note?.latitude != 0.0) {
                tvLocation.visibility = View.VISIBLE
                val addr = getAddress(note!!.latitude, note!!.longitude)
                tvLocation.text = addr
            }
        }

        // Set OnClickListener
        val btnSave = findViewById<Button>(R.id.btnSave)
        btnSave.setOnClickListener(this)

        // Init FusedLocationProvider
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun getAddress(lat: Double, lng: Double): String? {
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocation(lat,lng,1)
        return list[0].getAddressLine(0)
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            101)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateLastLocation()
            }
        }
    }

    private fun updateLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions()
            return
        }

        fusedLocationClient.lastLocation.addOnCompleteListener(this) {
                task ->  lastLocation = task.result
        }
    }

    override fun onResume() {
        super.onResume()

        updateLastLocation()
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
            lastLocation?.let {
                latitude = it.latitude
                longitude = it.longitude
            }

            db.insertNote(Note(0, System.currentTimeMillis(), title, message, latitude, longitude))
        } else {
            note?.message = message
            note?.title = title
            db.updateNote(note!!)
        }

        // Display Toast
        Toast.makeText(this, "Latitude: $latitude Longitude: $longitude", Toast.LENGTH_LONG).show()
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