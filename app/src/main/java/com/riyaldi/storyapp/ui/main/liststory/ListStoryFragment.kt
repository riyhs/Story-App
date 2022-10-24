package com.riyaldi.storyapp.ui.main.liststory

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.databinding.FragmentListStoryBinding
import com.riyaldi.storyapp.utils.Preference

class ListStoryFragment : Fragment() {

    private var _binding: FragmentListStoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: StoriesAdapter
    private val listStoryViewModel: ListStoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListStoryBinding.inflate(inflater, container, false)
        postponeEnterTransition()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val sharedPref = Preference.initPref(requireContext(), "onSignIn")

        listStoryViewModel.setStories(sharedPref.getString("token", "").toString())
        listStoryViewModel.getStories().observe(requireActivity()) { data ->
            if (data != null) {
                adapter.submitList(data.listStory)
            }
        }

        listStoryViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        setupAdapter()

        binding.fabCreateStory.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.createStoryFragment))

        onBackPressed()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvStories.setHasFixedSize(true)
        binding.rvStories.layoutManager = layoutManager

        adapter = StoriesAdapter{story, imageView, nameView, descView ->
            val action = ListStoryFragmentDirections.actionListStoryFragmentToDetailStoryFragment(
                id = story.id,
                name = story.name,
                description = story.description,
                photoUrl = story.photoUrl
            )

            findNavController()
                .navigate(action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElements(
                            mapOf(
                                imageView to imageView.transitionName,
                                nameView to nameView.transitionName,
                                descView to descView.transitionName,
                            )
                        ).build()
                )
        }

        binding.rvStories.adapter = adapter

        binding.rvStories.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }

    private fun showLoading(state: Boolean) {
        binding.pbListStory.isVisible = state
        binding.rvStories.isInvisible = state
        binding.fabCreateStory.isInvisible = state
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}