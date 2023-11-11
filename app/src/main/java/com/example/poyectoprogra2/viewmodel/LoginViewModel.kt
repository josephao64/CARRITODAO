package com.example.poyectoprogra2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // LiveData o StateFlow para observar los cambios en la UI
    private val _user = MutableStateFlow(auth.currentUser)
    val user = _user.asStateFlow()

    fun signInWithGoogle(account: GoogleSignInAccount) {
        viewModelScope.launch {
            try {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                val authResult = auth.signInWithCredential(credential).await()
                _user.value = authResult.user
            } catch (e: Exception) {
                // Manejar el error de autenticación
            }
        }
    }

    fun signOut() {
        auth.signOut()
        _user.value = null
    }
}
