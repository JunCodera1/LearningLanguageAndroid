package com.example.learninglanguage.utils

object Validation {

    private val emailRegex = Regex("^.+@.+\\..+$")


    private val passwordRegex = Regex("^.{8,}$")


    private val nameRegex = Regex("^[\\p{L}\\s]+$")

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex)
    }

    fun isValidPassword(password: String): Boolean {
        return password.matches(passwordRegex)
    }

    fun isValidName(name: String): Boolean {
        return name.matches(nameRegex)
    }
}
