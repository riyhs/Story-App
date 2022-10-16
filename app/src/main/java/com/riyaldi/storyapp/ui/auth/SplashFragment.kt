package com.riyaldi.storyapp.ui.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.Preference
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.riyaldi.storyapp.R

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPref = com.riyaldi.storyapp.utils.Preference.initPref(requireContext(), "onSignIn")
        val token = sharedPref.getString("token", "")

        var action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()

        if (token != "") {
            action = SplashFragmentDirections.actionSplashFragmentToMainActivity()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(action)
        }, 750)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
}