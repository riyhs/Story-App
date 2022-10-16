package com.riyaldi.storyapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.databinding.FragmentListStoryBinding
import com.riyaldi.storyapp.databinding.FragmentSignUpBinding
import com.riyaldi.storyapp.utils.Preference

class ListStoryFragment : Fragment() {

    private var _binding: FragmentListStoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListStoryBinding.inflate(inflater, container, false)

        binding.btDetailStory.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.detailStoryFragment))
        binding.fabCreateStory.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.createStoryFragment))

        val sharedPref = Preference.initPref(requireContext(), "onSignIn")
        val token = sharedPref.getString("token", "token")

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}