package com.example.projemanag.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()


        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        binding.btnSignInSignIn.setOnClickListener {
            signInRegisteredUser()
        }

        setupActionBar()
    }

    private fun setupActionBar() {

        setSupportActionBar(findViewById(R.id.toolbar_sign_in_activity))

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }

        binding.toolbarSignInActivity.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun signInRegisteredUser() {
        val email: String = binding.etEmailSignIn.text.toString().trim { it <= ' ' }
        val password: String = binding.etPasswordSignIn.text.toString().trim { it <= ' ' }

        if (validateForm(email, password)) {
            showProgressDialog(resources.getString(R.string.please_wait))
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        hideProgressDialog()
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Sign in", "signInWithEmail:success")
                            val user = auth.currentUser
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Sign in", "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()

                        }
                    }
        }

    }

    private fun validateForm(email: String, password: String): Boolean {
        return when {

            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter an email adress")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter the password")
                false
            }
            else -> {
                true
            }
        }
    }

}