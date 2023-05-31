package com.prof.reda.android.project.googlenews.ui.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.databinding.FragmentSignInBinding
import com.prof.reda.android.project.googlenews.databinding.FragmentSignupBinding
import com.prof.reda.android.project.googlenews.ui.activities.HomeActivity
import java.util.regex.Pattern


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private var reference: DatabaseReference ?= null
    private var userId:String ?= null
    private var dialog:ProgressDialog ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        if (reference == null){
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            reference= database.getReference("Users")
        }
        dialog = ProgressDialog(activity)
        binding.goToLogInBtn.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameAuthContainer, SignInFragment())?.commit()
        }

        binding.signupBtn.setOnClickListener{
            if (isValidate()){
                init()
                signUp(binding.emailEt.text.toString(), binding.passwordEt.text.toString())
            }
        }

        return binding.root
    }

    private fun init(){
        binding.firstNameOutline.isErrorEnabled = false
        binding.lastNameOutline.isErrorEnabled = false
        binding.emailOutline.isErrorEnabled = false
        binding.passwordOutline.isErrorEnabled = false
    }
    private fun signUp(email:String, password:String) {
        dialog?.setMessage("SignUp Success")
        dialog?.show()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful){
                    firebaseAuth.currentUser?.sendEmailVerification()
                        ?.addOnCompleteListener{task ->
                            if (task.isSuccessful) {
                                val user: FirebaseUser = firebaseAuth.currentUser!!
                                if (user != null){
                                    userId = user.uid
                                }

                                val firstName = binding.firstNameEt.text.toString()
                                val lastName = binding.lastNameEt.text.toString()
                                val email = binding.emailEt.text.toString()

                                reference = FirebaseDatabase.getInstance().getReference("Users")
                                .child(userId!!)

                                val hashMap: HashMap<String,String> = HashMap()
                                hashMap.put("id", userId!!)
                                hashMap.put("firstName", firstName)
                                hashMap.put("lastName", lastName)
                                hashMap.put("email", email)

                                reference!!.push().setValue(hashMap)
                                    .addOnCompleteListener{task ->
                                        if (task.isSuccessful){
                                            Toast.makeText(activity, "Successful Registration", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    }

                            }
                        }

                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.frameAuthContainer, SignInFragment())?.commit()
                }else{
                    Toast.makeText(activity, "Failed Registration", Toast.LENGTH_SHORT).show()
                }
                dialog?.dismiss()
            }
    }

    private fun isValidate():Boolean{
        if (TextUtils.isEmpty(binding.firstNameEt.text.toString())){
            binding.firstNameOutline.error = "First name required"
            binding.firstNameOutline.isErrorEnabled = true
            return false
        }
        if (TextUtils.isEmpty(binding.lastNameEt.text.toString())){
            binding.lastNameOutline.error = "Last name required"
            binding.lastNameOutline.isErrorEnabled = true
            return false
        }
        if (TextUtils.isEmpty(binding.emailEt.text.toString()) &&
            !EMAIL_ADDRESS_PATTERN.matcher(binding.emailEt.text.toString()).matches()){
            binding.emailOutline.error = "Enter validate Email"
            binding.emailOutline.isErrorEnabled = true
            return false
        }
        if (TextUtils.isEmpty(binding.passwordEt.text.toString())){
            binding.passwordOutline.error = "Enter Password"
            binding.passwordOutline.isErrorEnabled = true
            return false
        }
        return true
    }

    private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
}