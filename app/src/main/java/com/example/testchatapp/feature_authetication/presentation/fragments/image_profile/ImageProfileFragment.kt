package com.example.testchatapp.feature_authetication.presentation.fragments.image_profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.testchatapp.R
import com.example.testchatapp.databinding.FragmentImageProfileBinding
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class ImageProfileFragment : Fragment() {
    val action = ProfileFragmnetListener()
    companion object {
        fun newInstance() = ImageProfileFragment()
    }

    private val viewModel: ImageProfileViewModel by viewModels()
    lateinit var ui: FragmentImageProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       ui = FragmentImageProfileBinding.inflate(inflater, container, false)
        return ui.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         action.checkUser(ui,activity)

    }




}