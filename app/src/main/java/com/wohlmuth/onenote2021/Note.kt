package com.wohlmuth.onenote2021

data class Note(val id: Long, val timestamp: Long, var title: String, var message: String, var latitude: Double, var longitude: Double)