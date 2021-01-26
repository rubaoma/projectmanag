package com.example.projemanag.firebase

import com.example.projemanag.databinding.ActivitySignUpBinding
import com.example.projemanag.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreClass {

     // Access a Cloud Firestore instance from your Activity
   private val db = Firebase.firestore

    fun registerUser(activitySignUpBinding: ActivitySignUpBinding, user: User){

    }
}