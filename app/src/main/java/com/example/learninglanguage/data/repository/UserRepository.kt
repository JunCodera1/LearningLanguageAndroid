package com.example.learninglanguage.data.repository

import com.example.learninglanguage.data.model.User
import java.util.UUID

interface UserRepository {
    suspend fun createUser(user: User): User
    suspend fun findUserByEmail(email: String): User?
    suspend fun findUserById(id: UUID): User?
    suspend fun updateUser(user: User): User
}