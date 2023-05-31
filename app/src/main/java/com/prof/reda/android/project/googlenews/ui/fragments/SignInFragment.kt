package com.prof.reda.android.project.googlenews.ui.fragments

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.databinding.FragmentSignInBinding
import com.prof.reda.android.project.googlenews.ui.activities.HomeActivity

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private var firebaseAuth: FirebaseAuth ?= null
    private var dialog: ProgressDialog ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        dialog = ProgressDialog(activity)

        binding.signInBtn.setOnClickListener{
            login(binding.emailEt.text.toString(), binding.passwordEt.text.toString())
        }

        binding.forgottenPasswordTv.setOnClickListener {
            val user = firebaseAuth!!.currentUser
            if (user != null) {
                firebaseAuth!!.sendPasswordResetEmail(user.email.toString())
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful){
                            Toast.makeText(activity, "Email Send", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.createNewAccountTv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameAuthContainer, SignupFragment())?.commit()
        }
        return binding.root
    }

    private fun login(email:String, password:String) {
        dialog?.setMessage("Logging in")
        firebaseAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener{task ->
                if (task.isSuccessful){
                    val sharedPreferences = activity?.getSharedPreferences("User", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                    editor.putBoolean("isLoggedIn", true)
                    val user = firebaseAuth!!.currentUser
                    startActivity(Intent(activity, HomeActivity::class.java))
                }
            }
    }
}