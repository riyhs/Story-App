package com.riyaldi.storyapp.ui.auth.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.riyaldi.storyapp.R
import com.riyaldi.storyapp.data.Result
import com.riyaldi.storyapp.data.remote.response.login.LoginResponse
import com.riyaldi.storyapp.databinding.FragmentLoginBinding
import com.riyaldi.storyapp.utils.Preference
import com.riyaldi.storyapp.utils.ViewModelFactory

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLoginDontHaveAccount.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.signUpFragment))

        binding.btLogin.setOnClickListener{
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edLoginPassword.text.toString()

            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)

            loginViewModel.login(email, password).observe(requireActivity()) { result ->
                if (result != null) {
                    when(result) {
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Success -> {
                            processLogin(result.data)
                            showLoading(false)
                        }
                        is Result.Error -> {
                            showLoading(false)
                            Toast.makeText(requireContext(), result.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        val isFromSignUp: Boolean? = arguments?.getBoolean("is_from_sign_up")
        if (isFromSignUp != null && isFromSignUp) {
            onBackPressed()
        }
    }

    private fun processLogin(data: LoginResponse) {
        if (data.error) {
            Toast.makeText(requireContext(), data.message, Toast.LENGTH_LONG).show()
        } else {
            Preference.saveToken(data.loginResult.token, requireContext())
            findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
            requireActivity().finish()
        }
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    private fun showLoading(state: Boolean) {
        binding.pbLogin.isVisible = state
        binding.edLoginEmail.isInvisible = state
        binding.edLoginPassword.isInvisible = state
        binding.btLogin.isInvisible = state
        binding.textView6.isInvisible = state
        binding.tvLoginDontHaveAccount.isInvisible = state
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}