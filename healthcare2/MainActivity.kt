package com.nibm.healthcare2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().reference

        // Navigate to Splash Screen First
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish() // Close MainActivity after navigating
    }
}