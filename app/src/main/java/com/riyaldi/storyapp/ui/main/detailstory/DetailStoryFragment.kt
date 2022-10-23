package com.riyaldi.storyapp.ui.main.detailstory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import coil.load
import com.riyaldi.storyapp.databinding.FragmentDetailStoryBinding
import kotlinx.android.synthetic.main.fragment_detail_story.*
import kotlinx.android.synthetic.main.fragment_list_story.*

class DetailStoryFragment : Fragment() {

    private var _binding: FragmentDetailStoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        val imageUrl = arguments?.getString("photo_url")

        iv_detail_photo.apply {
            transitionName = imageUrl
            load(imageUrl){
                allowHardware(false)
            }
        }

//        binding.ivDetailPhoto.apply {
//            transitionName = imageUrl
//            load(imageUrl){
//                allowHardware(false)
//
//            }
//        }
        binding.tvDetailName.text = arguments?.getString("name")
        binding.tvDetailDescription.text = arguments?.getString("description")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}