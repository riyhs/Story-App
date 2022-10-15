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

    companion object {
        private var STATUS = "status";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper()).postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToSignUpFragment()
            findNavController().navigate(action)
        }, 750)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun onSignInFinished() {
//        val sharedPref = Preference.initPref(requireContext(), "onSignIn")
//        TOKEN_KEY = sharedPref.getString("token", "token").toString()
//        STATUS = sharedPref.getString("status", "status").toString()
    }
}