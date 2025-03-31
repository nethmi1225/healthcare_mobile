package com.nibm.healthcare2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DoctorProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_profile)

        // Get doctor data from intent
        val name = intent.getStringExtra("DOCTOR_NAME") ?: "Unknown Doctor"
        val specialty = intent.getStringExtra("DOCTOR_SPECIALTY") ?: "Unknown Specialty"
        val bio = intent.getStringExtra("DOCTOR_BIO") ?: "No bio available"
        val experience = intent.getStringExtra("DOCTOR_EXPERIENCE") ?: "N/A"
        val email = intent.getStringExtra("DOCTOR_EMAIL") ?: "No email"

        // Populate UI
        findViewById<TextView>(R.id.doctorProfileName).text = name
        findViewById<TextView>(R.id.doctorProfileSpecialty).text = specialty
        findViewById<TextView>(R.id.doctorProfileBio).text = bio
        findViewById<TextView>(R.id.doctorProfileExperience).text = "Experience: $experience"
        findViewById<TextView>(R.id.doctorProfileEmail).text = "Email: $email"

        // Back button
        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}

