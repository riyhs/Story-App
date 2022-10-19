package com.riyaldi.storyapp.ui.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.utils.Preference

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = Preference.initPref(requireContext(), "onSignIn")
        val token = sharedPref.getString("token", "")

        var action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()

        if (token != "") {
            action = SplashFragmentDirections.actionSplashFragmentToMainActivity()
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(action)
                requireActivity().finish()
            }, 750)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(action)
            }, 750)
        }
    }
}