package com.riyaldi.storyapp.ui.main.liststory

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.databinding.FragmentListStoryBinding
import com.riyaldi.storyapp.databinding.FragmentSignUpBinding
import com.riyaldi.storyapp.model.stories.Story
import com.riyaldi.storyapp.ui.auth.login.LoginViewModel
import com.riyaldi.storyapp.utils.Preference
import kotlinx.android.synthetic.main.fragment_list_story.*

class ListStoryFragment : Fragment() {

    private var _binding: FragmentListStoryBinding? = null
    private val binding get() = _binding!!

    private val listStoryViewModel: ListStoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        rv_stories.adapter = StoriesAdapter(listOf(Story("", "", "", 0.0, 0.0, "", "")))

        binding.fabCreateStory.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.createStoryFragment))

        val sharedPref = Preference.initPref(requireContext(), "onSignIn")
        listStoryViewModel.setStories(sharedPref.getString("token", "").toString())
        listStoryViewModel.getStories().observe(requireActivity()) { data ->
            if (data != null) {
                Log.d("LIST_STORY", data.toString())
                rv_stories.adapter = StoriesAdapter(data.listStory)
            }
        }

        onBackPressed()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_stories.setHasFixedSize(true)
        rv_stories.layoutManager = layoutManager
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}