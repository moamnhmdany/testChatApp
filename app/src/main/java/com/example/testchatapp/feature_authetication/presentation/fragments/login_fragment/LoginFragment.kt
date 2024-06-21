package com.example.testchatapp.feature_authetication.presentation.fragments.login_fragment

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.testchatapp.R
import com.example.testchatapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }
    private val action = LoginFragmentListener()
    lateinit var ui : FragmentLoginBinding
    lateinit var context: FragmentActivity
    private lateinit var viewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
           ui = FragmentLoginBinding.inflate(layoutInflater, container, false)
           context = requireActivity()
       viewModel = LoginViewModel(context)

        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        action.checkUser(context)

        action.login(ui,context,viewModel)

        action.openSignUp(context,ui)
    }


}