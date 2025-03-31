package com.nibm.healthcare2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DoctorAdapter

    // Sample doctor list (replace with real data source, e.g., Firebase)
    private val doctorList = listOf(
        Doctor("Dr. Jane Smith", "Cardiologist", "Specializes in heart conditions", "10 years", "jane.smith@example.com"),
        Doctor("Dr. Mark Lee", "Neurologist", "Expert in brain disorders", "8 years", "mark.lee@example.com"),
        Doctor("Dr. Sarah Brown", "Pediatrician", "Child healthcare specialist", "12 years", "sarah.brown@example.com")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.doctorsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = DoctorAdapter(doctorList) { doctor ->
            // Navigate to DoctorProfileActivity with doctor data
            val intent = Intent(requireContext(), DoctorProfileActivity::class.java).apply {
                putExtra("DOCTOR_NAME", doctor.name)
                putExtra("DOCTOR_SPECIALTY", doctor.specialty)
                putExtra("DOCTOR_BIO", doctor.bio)
                putExtra("DOCTOR_EXPERIENCE", doctor.experience)
                putExtra("DOCTOR_EMAIL", doctor.email)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        return view
    }
}

// Data class for a doctor
data class Doctor(
    val name: String,
    val specialty: String,
    val bio: String,
    val experience: String,
    val email: String
)

// RecyclerView Adapter for doctors
class DoctorAdapter(
    private val doctors: List<Doctor>,
    private val onDoctorClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.bind(doctor)
        holder.itemView.setOnClickListener { onDoctorClick(doctor) }
    }

    override fun getItemCount(): Int = doctors.size

    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val doctorName = itemView.findViewById<TextView>(R.id.doctorName)
        private val doctorSpecialty = itemView.findViewById<TextView>(R.id.doctorSpecialty)

        fun bind(doctor: Doctor) {
            doctorName.text = doctor.name
            doctorSpecialty.text = doctor.specialty
        }
    }
}