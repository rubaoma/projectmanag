package com.example.projemanag.activities

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.projemanag.R
import com.example.projemanag.databinding.ActivityBaseBinding
import com.example.projemanag.databinding.DialogProgressBinding


open class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding
    private var doubleBackToExitPressedOnce = false
    private lateinit var dialogBinding: DialogProgressBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Handler(Looper.getMainLooper()).postDelayed({

        }, 2500)

    }

    fun showProgressDialog(text: String) {
        val mProgressDialog = Dialog(this)
        dialogBinding = DataBindingUtil.setContentView(this, R.layout.dialog_progress)

        mProgressDialog.setContentView(dialogBinding.root)
        dialogBinding.tvProgressText.text = text

        mProgressDialog.show()

        
    }

    fun hideProgressDialog(){
    }
/*
    open fun doSomething() {
        val binding: DialogTestBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.dialog_test, null, false)
        val dialog = Dialog(context)
        dialog.setContentView(binding.getRoot())
        dialog.show()
    }
*/
}