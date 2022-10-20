package com.riyaldi.storyapp.ui.main.createstory

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.databinding.FragmentCreateStoryBinding
import com.riyaldi.storyapp.utils.rotateBitmap

class CreateStoryFragment : Fragment() {
    private var _binding: FragmentCreateStoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btOpenCamera.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.cameraFragment))

        val fileUri = arguments?.get("selected_image")
        if (fileUri != null) {
            val uri: Uri = fileUri as Uri
            val isBackCamera = arguments?.get("isBackCamera") as Boolean
            val result = rotateBitmap(
                BitmapFactory.decodeFile(uri.path),
                isBackCamera
            )
            binding.ivImagePreview.setImageBitmap(result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}