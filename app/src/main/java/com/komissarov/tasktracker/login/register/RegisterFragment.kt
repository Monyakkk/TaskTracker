package com.komissarov.tasktracker.login.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.komissarov.tasktracker.App
import com.komissarov.tasktracker.R
import com.komissarov.tasktracker.databinding.FragmentRegisterBinding
import com.komissarov.tasktracker.login.check
import com.komissarov.tasktracker.login.checkEmail
import com.komissarov.tasktracker.login.confirm.ConfirmationFragment.Companion.ARG_EMAIL
import com.komissarov.tasktracker.login.confirm.ConfirmationFragment.Companion.ARG_PASS
import javax.inject.Inject

class RegisterFragment : Fragment(R.layout.fragment_register) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var educationalProgramsArray: ArrayList<String> = ArrayList()
    private var educationalProgramsMap: MutableMap<Int, String> = mutableMapOf()
    private var fieldsArray: ArrayList<String> = ArrayList()
    private var fieldsMap: MutableMap<Int, String> = mutableMapOf()
    private val viewModel by viewModels<RegisterViewModel> { viewModelFactory }
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.educationalProgramsList.observe(viewLifecycleOwner) { list ->
            for (item in list) {
                educationalProgramsMap[item.id] = item.enrollment_year.toString() + " " + item.title!!.toString()
                educationalProgramsArray.add(item.enrollment_year.toString() + " " + item.title!!.toString())
            }
        }

        viewModel.fieldsList.observe(viewLifecycleOwner) { list ->
            for (item in list) {
                fieldsMap[item.id] = item.number.toString() + " " + item.title!!.toString()
                fieldsArray.add(item.number.toString() + " " + item.title!!.toString())
            }
        }

        val plansArrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, educationalProgramsArray) //TODO try R.layout.item_dropdown
        binding.educationalProgram.setAdapter(plansArrayAdapter)

        val fieldsArrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, fieldsArray) //TODO try R.layout.item_dropdown
        binding.fieldOfStudy.setAdapter(fieldsArrayAdapter)

        binding.buttonRegister.setOnClickListener {
            if (binding.inputLastname.text.check() &&
                binding.inputFirstName.text.check() &&
                binding.inputPassword.text.check() &&
                binding.inputPassword.text?.length!! > 8 &&
                !binding.educationalProgram.text.isNullOrEmpty() &&
                !binding.fieldOfStudy.text.isNullOrEmpty() &&
                binding.inputEmail.text.check() &&
                binding.inputEmail.text.checkEmail()
            ) {
                binding.buttonRegister.isEnabled = false
                viewModel.register(
                    lastName = binding.inputLastname.text.toString(),
                    firstName = binding.inputFirstName.text.toString(),
                    thirdName = binding.inputThirdname.text.toString(),
                    email = binding.inputEmail.text.toString(),
                    password = binding.inputPassword.text.toString(),
                    educationalProgramId = educationalProgramsMap.entries.find { it.value == binding.educationalProgram.text.toString() }?.key ?: 0,
                    fieldOfStudyId = fieldsMap.entries.find { it.value == binding.fieldOfStudy.text.toString() }?.key ?: 0
                )

                //REMOVE
                val bundle = bundleOf(
                    ARG_EMAIL to binding.inputEmail.text.toString(),
                    ARG_PASS to binding.inputPassword.text.toString()
                )
                findNavController().navigate(
                    R.id.action_registerFragment_to_confirmationFragment,
                    bundle
                )

            } else {
                Toast.makeText(requireContext(), "Ошибка в введенных данных", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.isRegisterSuccessful.observe(viewLifecycleOwner) {
            if (it) {
                val bundle = bundleOf(
                    ARG_EMAIL to binding.inputEmail.text.toString(),
                    ARG_PASS to binding.inputPassword.text.toString()
                )
                findNavController().navigate(
                    R.id.action_registerFragment_to_confirmationFragment,
                    bundle
                )
            } else {
                binding.buttonRegister.isEnabled = true
            }
        }

        viewModel.getEducationalPrograms()
        viewModel.getFieldsOfStudy()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}