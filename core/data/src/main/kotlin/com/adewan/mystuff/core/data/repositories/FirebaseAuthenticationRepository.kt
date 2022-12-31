package com.adewan.mystuff.core.data.repositories

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthenticationRepository(private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()) {
    fun signIn(activity: Context) {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(activity, options)
        (activity as Activity).startActivityForResult(client.signInIntent, 123)
    }
}
