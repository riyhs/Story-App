package com.riyaldi.storyapp.ui.main.liststory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.databinding.FragmentListStoryBinding
import com.riyaldi.storyapp.model.stories.Story
import com.riyaldi.storyapp.utils.Preference
import kotlinx.android.synthetic.main.fragment_list_story.*

class ListStoryFragment : Fragment() {

    private var _binding: FragmentListStoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var storiesAdapter: StoriesAdapter
    private val listStoryViewModel: ListStoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storiesAdapter = StoriesAdapter(
            listOf(Story("", "", "", 0.0, 0.0, "", "")),
            object : StoriesAdapter.StorySelectedListener {
                override fun onStorySelected(name: String, description: String, url: String, storyImageView: ImageView) {}
            }
        )

        setupAdapter()

        binding.fabCreateStory.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.createStoryFragment))

        val sharedPref = Preference.initPref(requireContext(), "onSignIn")
        listStoryViewModel.setStories(sharedPref.getString("token", "").toString())
        listStoryViewModel.getStories().observe(requireActivity()) { data ->
            if (data != null) {
                rv_stories.adapter = StoriesAdapter(
                    data.listStory,
                    object : StoriesAdapter.StorySelectedListener {
                        override fun onStorySelected(
                            name: String,
                            description: String,
                            url: String,
                            storyImageView: ImageView
                        ) {
                            val extras = FragmentNavigatorExtras(
                                storyImageView to url
                            )
                            val action = ListStoryFragmentDirections.actionListStoryFragmentToDetailStoryFragment(
                                name = name,
                                description = description,
                                photoUrl = url
                            )
                            findNavController().navigate(action, extras)
                        }
                    }
                )
            }
        }

        onBackPressed()
    }

    private fun setupAdapter() {
        val lm = LinearLayoutManager(context)
        lm.orientation = LinearLayoutManager.VERTICAL

        postponeEnterTransition()
        rv_stories.apply {
            setHasFixedSize(true)
            layoutManager = lm
            adapter = storiesAdapter
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
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