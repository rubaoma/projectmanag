package com.example.projemanag.firebase

import com.example.projemanag.databinding.ActivitySignUpBinding
import com.example.projemanag.model.User
import com.example.projemanag.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreClass {

     // Access a Cloud Firestore instance from your Activity
   private val db = Firebase.firestore

    fun registerUser(activitySignUpBinding: ActivitySignUpBinding, userInfo: User){
        db.collection(Constants.USERS)
                .document(getCurrentUserId()).set(userInfo, SetOptions.merge())
                .addOnSuccessListener {
                    activitySignUpBinding.userRegisteredSuccess()
                }
    }

    fun getCurrentUserId(): String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}