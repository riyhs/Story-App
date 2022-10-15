package com.riyaldi.storyapp.ui.auth.signup

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.databinding.FragmentSignUpBinding
import com.riyaldi.storyapp.databinding.FragmentSplashBinding

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.tvSignupHaveAccount.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.loginFragment))

        binding.btSignUp.setOnClickListener{
            val name = binding.etSignupName.text.toString()
            val email = binding.etSignupEmail.text.toString()
            val password = binding.etSignupPassword.text.toString()

            signUpViewModel.postSignUp(name, email, password)

            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        signUpViewModel.isSignUpFailed.observe(requireActivity()) {
            processSignUp(it)
        }

        return binding.root
    }

    private fun processSignUp(isFailed: Boolean) {
        if (isFailed) {
            Toast.makeText(requireContext(), "Gagal SignUp", Toast.LENGTH_SHORT).show()
        } else {
            findNavController().navigate(R.id.action_signUpFragment_to_mainActivity)
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}