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

class IntentFragment : Fragment() {

    private lateinit var goalViewModel: GoalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_intent, container, false)

        goalViewModel = ViewModelProvider(requireActivity()).get(GoalViewModel::class.java)

        val intentRadioGroup = view.findViewById<RadioGroup>(R.id.intent_radio_group)
        val nextButton = view.findViewById<Button>(R.id.btn_next_intent)

        nextButton.setOnClickListener {
            val selectedIntent = view.findViewById<RadioButton>(
                intentRadioGroup.checkedRadioButtonId
            ).text.toString()

            // Save the intent in the GoalViewModel
            goalViewModel.updateIntent(selectedIntent)

            // Navigate to the next fragment (e.g., LocationFragment)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LocationFragment())
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
