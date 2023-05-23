package com.prof.reda.android.project.googlenews.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prof.reda.android.project.googlenews.R
import com.prof.reda.android.project.googlenews.ui.fragments.SignupFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().replace(R.id.frameAuthContainer, SignupFragment()).commit()
    }
}