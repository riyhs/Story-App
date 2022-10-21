package com.riyaldi.storyapp.ui.auth.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.databinding.FragmentSignUpBinding
import com.riyaldi.storyapp.model.signup.SignUpResponse

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignupHaveAccount.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.loginFragment))

        binding.btSignUp.setOnClickListener{
            val name = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.text.toString()

            signUpViewModel.postSignUp(name, email, password)

            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        signUpViewModel.signUpResponse.observe(requireActivity()) {
            if (it != null) {
                processSignUp(it)
            }
        }
    }

    private fun processSignUp(data: SignUpResponse) {
        if (data.error) {
            Toast.makeText(requireContext(), "Gagal Sign Up", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Sign Up berhasil, silahkan login!", Toast.LENGTH_LONG).show()
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment(isFromSignUp = true))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}