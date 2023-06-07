package com.komissarov.tasktracker.login.confirm

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.mainpage.MainActivity
import com.poovam.pinedittextfield.SquarePinField
import javax.inject.Inject

class ConfirmationFragment : Fragment(R.layout.fragment_confirmation) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ConfirmationViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email: String = requireArguments().getString(ARG_EMAIL) ?: return
        val password: String = requireArguments().getString(ARG_PASS) ?: return
        val pin: SquarePinField = view.findViewById(R.id.pinField)
        val buttonConfirm: Button = view.findViewById(R.id.buttonConfirm)

        viewModel.isConfirmSuccessful.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Подтвержднено успешно", Toast.LENGTH_SHORT).show()
                viewModel.verifyUser(email, password)
            } else {
                pin.isEnabled = true
                buttonConfirm.isEnabled = true
            }
        }

        viewModel.isRegisterSuccessful.observe(viewLifecycleOwner) {
            if (it) {
                startActivity(MainActivity.provideScreen(requireActivity().baseContext))
                activity?.finish()
            } else {
                buttonConfirm.isEnabled = true
                pin.isEnabled = true
                pin.text?.clear()
            }
        }

        buttonConfirm.setOnClickListener {
            pin.isEnabled = false
            buttonConfirm.isEnabled = false
            viewModel.postCode(pin.text.toString(), email)
        }
    }

    companion object {

        const val ARG_EMAIL = "email"
        const val ARG_PASS = "pass"
    }
}