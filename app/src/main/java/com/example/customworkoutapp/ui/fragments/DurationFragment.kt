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

class DurationFragment : Fragment() {

    private lateinit var goalViewModel: GoalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_duration, container, false)

        goalViewModel = ViewModelProvider(requireActivity()).get(GoalViewModel::class.java)

        val durationRadioGroup = view.findViewById<RadioGroup>(R.id.duration_radio_group)
        val nextButton = view.findViewById<Button>(R.id.btn_next_duration)

        nextButton.setOnClickListener {
            val selectedDurationText = view.findViewById<RadioButton>(
                durationRadioGroup.checkedRadioButtonId
            ).text.toString()

            // Strip non-numeric characters (e.g., "60 minutes" -> "60")
            val numericPart = selectedDurationText.replace(Regex("[^\\d]"), "")

            // Convert the numeric part to an integer, default to 0 if it fails
            val selectedDuration = numericPart.toIntOrNull() ?: 0

            // Update the duration in the ViewModel if it's a valid number
            if (selectedDuration > 0) {
                goalViewModel.updateDuration(selectedDuration)

                // Navigate to the next fragment (FrequencyFragment)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, FrequencyFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        return view
    }
}
