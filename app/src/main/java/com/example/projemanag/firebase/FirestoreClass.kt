package com.example.projemanag.firebase

import android.util.Log
import com.example.projemanag.activities.SignInActivity
import com.example.projemanag.activities.SignUpActivity
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

    fun registerUser(activitySignUpBinding: SignUpActivity, userInfo: User) {
        db.collection(Constants.USERS)
                .document(getCurrentUserId())
                .set(userInfo, SetOptions.merge())
                .addOnSuccessListener {
                    activitySignUpBinding.userRegisteredSuccess()
                }.addOnFailureListener {
                    Log.e(activitySignUpBinding.javaClass.simpleName, "Error writing document")
                }
    }

    fun signInUser(activity: SignInActivity) {
        db.collection(Constants.USERS)
                .document(getCurrentUserId())
                .get()
                .addOnSuccessListener {document ->
                    val loggedInUser = document.toObject(User::class.java)
                    if (loggedInUser != null)
                    activity.signInSuccess(loggedInUser)

                }.addOnFailureListener {
                    Log.e("SignInError", "Error writing document")
                }
    }

    fun getCurrentUserId(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if(currentUser != null){
            currentUserID = currentUser.uid
        }
            return currentUserID
    }
}