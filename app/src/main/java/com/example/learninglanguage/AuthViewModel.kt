package com.example.learninglanguage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init{
        checkAuthStatus()
    }
    fun checkAuthStatus(){
        if(auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated
        }
        else{
            _authState.value = AuthState.Authenticated
        }
    }
    fun login(email: String, password: String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Please fill in all fields!")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }
                else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signup(name: String, email: String, password: String) {
        if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            _authState.value = AuthState.Error("Please fill in all fields!")
            return
        }
        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()

                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            _authState.value = AuthState.Authenticated
                        } else {
                            _authState.value = AuthState.Error(updateTask.exception?.message ?: "Failed to update profile")
                        }
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

}

sealed class AuthState{
    object Authenticated: AuthState()
    object Unauthenticated: AuthState()
    object Loading: AuthState()
    data class Error(val message: String): AuthState()
}