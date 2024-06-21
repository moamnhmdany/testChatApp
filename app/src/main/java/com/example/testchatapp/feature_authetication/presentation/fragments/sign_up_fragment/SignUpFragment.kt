package com.example.testchatapp.feature_authetication.presentation.fragments.sign_up_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.testchatapp.R
import com.example.testchatapp.databinding.FragmentLoginBinding
import com.example.testchatapp.databinding.FragmentSignUpBinding
import com.example.testchatapp.feature_authetication.domain.use_case.UserOpareations
import com.example.testchatapp.feature_authetication.presentation.fragments.login_fragment.LoginFragmentListener
import com.example.testchatapp.feature_authetication.presentation.fragments.login_fragment.LoginViewModel

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }
    private val action = SignUpFragmentListener()
    lateinit var ui : FragmentSignUpBinding
    lateinit var context: FragmentActivity
    private lateinit var viewModel : SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ui = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        context = requireActivity()
        viewModel = SignUpViewModel(context)

        return ui.root
    }

//
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        action.signUp(ui,viewModel,context,context)
        action.openLogin(ui)
       // UserOpareations.checkUserState(context)

    }
}