package com.iamnaran.firefly.ui.auth.core

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.iamnaran.firefly.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GoogleAuthClient @Inject constructor(
    private val context: Context,
    private val signInClient: SignInClient,
    private val firebaseAuth: FirebaseAuth

) {
    suspend fun signIn(): IntentSender? {
        val result = try {
            signInClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = signInClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = firebaseAuth.signInWithCredential(googleCredentials).await().user
            if (user != null) {
                val userData = UserData(
                    userId = user.uid,
                    name = user.displayName.toString(),
                    email = user.email.toString(),
                    profilePic = user.photoUrl?.toString()
                )
                SignInResult(userData, "")
            } else {
                SignInResult(null, "")

            }

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message.toString()
            )
        }
    }

    suspend fun signOut() {
        try {
            signInClient.signOut().await()
            firebaseAuth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = firebaseAuth.currentUser?.run {
        UserData(
            userId = uid,
            name = displayName.toString(),
            email = email.toString(),
            profilePic = photoUrl?.toString()
        )
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}

data class SignInResult(
    val data: UserData?,
    val errorMessage: String)

data class UserData(
    val userId: String,
    val name: String,
    val email: String,
    val profilePic: String?
)