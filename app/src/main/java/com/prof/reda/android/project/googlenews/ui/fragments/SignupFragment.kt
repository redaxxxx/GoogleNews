package com.prof.reda.android.project.googlenews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.databinding.FragmentSignInBinding
import com.prof.reda.android.project.googlenews.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)


        binding.goToLogInBtn.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameAuthContainer, SignInFragment())?.commit()
        }

        binding.signupBtn.setOnClickListener{
            signUp()
        }

        return binding.root
    }

    private fun signUp() {

    }
}