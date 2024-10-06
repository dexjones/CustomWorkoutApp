package com.example.customworkoutapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.customworkoutapp.R
import com.example.customworkoutapp.viewmodel.GoalViewModel

class LocationFragment : Fragment() {

    private lateinit var goalViewModel: GoalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_location, container, false)

        goalViewModel = ViewModelProvider(requireActivity()).get(GoalViewModel::class.java)

        val locationRadioGroup = view.findViewById<RadioGroup>(R.id.location_radio_group)
        val nextButton = view.findViewById<Button>(R.id.btn_next_location)

        nextButton.setOnClickListener {
            val selectedLocation = view.findViewById<RadioButton>(
                locationRadioGroup.checkedRadioButtonId
            ).text.toString()

            goalViewModel.updateLocation(selectedLocation)

            // Navigate to the next fragment (EquipmentFragment)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EquipmentFragment())
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
