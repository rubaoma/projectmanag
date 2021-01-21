package com.example.projemanag.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setupActionBar()

    }


    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_sign_up_activity))

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }

        binding.toolbarSignUpActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.btnSignUp.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser(){
        val name: String = binding.etName.text.toString().trim { it <= ' '}
        val email: String = binding.etEmail.text.toString().trim { it <= ' '}
        val password: String = binding.etPassword.text.toString().trim { it <= ' '}

        if (validadeForm(name, email, password)){
            Toast.makeText(
                    this,
                    "Now we can register a new user",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun validadeForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter the name")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter an email adress")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter the password")
                false
            }else -> {
                true
            }
        }


    }
}