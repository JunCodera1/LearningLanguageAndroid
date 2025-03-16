package com.example.learninglanguage.model

/**
 * User data model that represents an authenticated user in the application
 *
 * @property uid Unique identifier from Firebase Authentication
 * @property name Display name of the user
 * @property email Email address of the user
 * @property photoUrl Optional URL to the user's profile image
 * @property language Optional preferred language for learning
 * @property level Optional current proficiency level
 * @property joinedDate Optional timestamp when the user joined
 */
data class User(
    val uid: String,
    val name: String,
    val email: String,
    val photoUrl: String? = null,
    val language: String? = null,
    val level: String? = null,
    val joinedDate: Long? = null
) {
    /**
     * Returns a displayable username, using email if name is not available
     */
    fun getDisplayName(): String {
        return if (name.isNotEmpty()) name else email.substringBefore("@")
    }

    /**
     * Returns initials for avatar placeholder when no photo is available
     */
    fun getInitials(): String {
        return if (name.isNotEmpty()) {
            name.split(" ")
                .filter { it.isNotEmpty() }
                .take(2)
                .joinToString("") { it.first().uppercase() }
        } else {
            email.first().uppercase()
        }
    }

    /**
     * Creates a simplified version of the user for displaying in UI
     */
    fun toDisplayUser(): DisplayUser {
        return DisplayUser(
            uid = uid,
            name = name,
            photoUrl = photoUrl
        )
    }

    companion object {
        /**
         * Creates an empty/anonymous user instance
         */
        fun createAnonymous(): User {
            return User(
                uid = "",
                name = "Guest",
                email = "",
                photoUrl = null
            )
        }
    }
}

/**
 * Lightweight user model for displaying in lists or UI components
 */
data class DisplayUser(
    val uid: String,
    val name: String,
    val photoUrl: String? = null
)