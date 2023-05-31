package com.prof.reda.android.project.googlenews.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.databinding.FragmentProfileBinding
import com.prof.reda.android.project.googlenews.ui.activities.AuthActivity

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()

        binding.LogoutLinearLayout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(activity, AuthActivity::class.java))
        }

        binding.changePassLinearLayout.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameProfile, ChangePasswordFragment())?.commit()
        }


        return binding.root
    }

}