package com.komissarov.tasktracker.login.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.databinding.FragmentLoginBinding
import com.komissarov.tasktracker.login.check
import com.komissarov.tasktracker.login.checkEmail
import com.komissarov.tasktracker.mainpage.MainActivity
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoginSuccessful.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(MainActivity.provideScreen(requireActivity().baseContext))
                activity?.finish()
            }
            binding.buttonLogin.isEnabled = true
        }
        binding.buttonLogin.setOnClickListener {
            if (binding.inputLoginEmail.text.checkEmail() && binding.inputLoginPassword.text.check()) {
                binding.buttonLogin.isEnabled = false
                viewModel.login(
                    binding.inputLoginEmail.text.toString(),
                    binding.inputLoginPassword.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}