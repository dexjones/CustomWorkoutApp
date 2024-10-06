package com.example.customworkoutapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.customworkoutapp.R
import com.example.customworkoutapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var tvUserName: TextView
    private lateinit var tvUserAge: TextView
    private lateinit var tvUserHeight: TextView
    private lateinit var tvUserWeight: TextView
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the TextViews
        tvUserName = view.findViewById(R.id.tv_user_name)
        tvUserAge = view.findViewById(R.id.tv_user_age)
        tvUserWeight = view.findViewById(R.id.tv_user_weight)

        // Initialize the UserViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Retrieve the userId from SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("userEmail", null)

        if (userEmail != null) {
            // Log or print to check if the email is retrieved
            println("Retrieved userEmail from SharedPreferences: $userEmail")

            // Fetch user data in a coroutine and update the UI
            viewLifecycleOwner.lifecycleScope.launch {
                val user = userViewModel.getUserByEmail(userEmail)
                user?.let {
                    // Update TextViews with user data
                    tvUserName.text = it.name
                    tvUserAge.text = it.age.toString()
                    tvUserWeight.text = it.weight.toString() + " lbs"
                }
            }
        }
        else {
            tvUserName.text = "No user data"
        }
    }
}
