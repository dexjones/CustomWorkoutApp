package com.example.customworkoutapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.customworkoutapp.R
import com.example.customworkoutapp.viewmodel.GoalViewModel

class EquipmentFragment : Fragment() {

    private lateinit var goalViewModel: GoalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_equipment, container, false)

        goalViewModel = ViewModelProvider(requireActivity()).get(GoalViewModel::class.java)

        val checkboxes = listOf(
            view.findViewById<CheckBox>(R.id.checkbox_power_rack),
            view.findViewById<CheckBox>(R.id.checkbox_dumbbells),
            view.findViewById<CheckBox>(R.id.checkbox_barbell),
            view.findViewById<CheckBox>(R.id.checkbox_bench),
            view.findViewById<CheckBox>(R.id.checkbox_resistance_bands),
            view.findViewById<CheckBox>(R.id.checkbox_machines),
            view.findViewById<CheckBox>(R.id.checkbox_cable_weights),
            view.findViewById<CheckBox>(R.id.checkbox_smith_machine)
        )

        val nextButton = view.findViewById<Button>(R.id.btn_next_equipment)

        nextButton.setOnClickListener {
            val selectedEquipment = checkboxes.filter { it.isChecked }.map { it.text.toString() }
            goalViewModel.updateEquipment(selectedEquipment)

            // Navigate to the next fragment (ExperienceFragment)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ExperienceFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
