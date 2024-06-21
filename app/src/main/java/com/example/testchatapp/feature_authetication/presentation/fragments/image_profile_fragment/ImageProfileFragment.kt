package com.example.testchatapp.feature_authetication.presentation.fragments.image_profile_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testchatapp.databinding.FragmentImageProfileBinding

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
        action.setupImage(ui, this)

        return ui.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         // action.checkUser(ui,activity)
        action.goUserListPage(ui,activity)
        action.goUserListPageNextButton(ui, activity,context)

    }




}