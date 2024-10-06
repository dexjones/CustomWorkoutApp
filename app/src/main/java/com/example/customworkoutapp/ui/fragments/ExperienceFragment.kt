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

class ExperienceFragment : Fragment() {

    private lateinit var goalViewModel: GoalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_experience, container, false)

        goalViewModel = ViewModelProvider(requireActivity()).get(GoalViewModel::class.java)

        val experienceRadioGroup = view.findViewById<RadioGroup>(R.id.experience_radio_group)
        val nextButton = view.findViewById<Button>(R.id.btn_next_experience)

        nextButton.setOnClickListener {
            val selectedExperience = view.findViewById<RadioButton>(
                experienceRadioGroup.checkedRadioButtonId
            ).text.toString()

            goalViewModel.updateExperience(selectedExperience)

            // Navigate to the next fragment (DurationFragment)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DurationFragment())
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
