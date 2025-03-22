package com.example.learninglanguage.data.repository

import com.example.learninglanguage.data.model.User
import java.util.UUID

class UserDbRepository : UserRepository {

    // Assume you have a database client or connection object here
    // For example, if using Exposed, it might look something like:
    // private val database = Database.connect(...)
    // Or if using a NoSQL library, it might be a client object

    override suspend fun createUser(user: User): User {
        // Database logic to create a user
        // 1. Prepare the data for insertion
        val newUser = user.copy(
            id = UUID.randomUUID(),
            createdAt = java.time.LocalDateTime.now(),
            updatedAt = java.time.LocalDateTime.now()
        )

        // 2. Execute the database insert operation
        // Example (this is illustrative, adapt to your library):
        // db.users.insertOne(newUser)
        // Or with Exposed:
        // transaction {
        //     Users.insert {
        //         it[id] = newUser.id
        //         it[email] = newUser.email
        //         it[passwordHash] = newUser.passwordHash
        //         it[fullName] = newUser.fullName
        //         it[avatarUrl] = newUser.avatarUrl
        //     }
        // }
        println("createUser : $newUser")

        // 3. Return the created user (possibly with the ID assigned by the database)
        return newUser
    }

    override suspend fun findUserByEmail(email: String): User? {
        // Database logic to find a user by email
        // 1. Query the database to find a user by email
        // Example (this is illustrative, adapt to your library):
        // val user = db.users.findOne("email" to email)
        // Or with Exposed:
        // val user = transaction {
        //     Users.select { Users.email eq email }.singleOrNull()?.let {
        //         User(
        //             id = it[Users.id],
        //             email = it[Users.email],
        //             passwordHash = it[Users.passwordHash],
        //             fullName = it[Users.fullName],
        //             avatarUrl = it[Users.avatarUrl]
        //         )
        //     }
        // }
        println("findUserByEmail: $email")

        // 2. Return the user (or null if not found)
        // return user
        return User(UUID.randomUUID(), email, "test", "test", "test") // remove this line and replace it by the previous return when the database logic is added
    }

    override suspend fun findUserById(id: UUID): User? {
        // Database logic to find a user by id
        // 1. Query the database to find a user by ID
        // Example (this is illustrative, adapt to your library):
        // val user = db.users.findOne("id" to id)
        // Or with Exposed:
        // val user = transaction {
        //     Users.select { Users.id eq id }.singleOrNull()?.let {
        //         User(
        //             id = it[Users.id],
        //             email = it[Users.email],
        //             passwordHash = it[Users.passwordHash],
        //             fullName = it[Users.fullName],
        //             avatarUrl = it[Users.avatarUrl]
        //         )
        //     }
        // }
        println("findUserById: $id")

        // 2. Return the user (or null if not found)
        return User(id, "test", "test", "test", "test") // remove this line and replace it by the previous return when the database logic is added
    }

    override suspend fun updateUser(user: User): User {
        // Database logic to update a user
        // 1. Prepare the data for update
        val updatedUser = user.copy(updatedAt = java.time.LocalDateTime.now())

        // 2. Execute the database update operation
        // Example (this is illustrative, adapt to your library):
        // db.users.updateOne("id" to updatedUser.id, updatedUser)
        // Or with Exposed:
        // transaction {
        //     Users.update({ Users.id eq updatedUser.id }) {
        //         it[email] = updatedUser.email
        //         it[passwordHash] = updatedUser.passwordHash
        //         it[fullName] = updatedUser.fullName
        //         it[avatarUrl] = updatedUser.avatarUrl
        //     }
        // }
        println("updateUser: $updatedUser")

        // 3. Return the updated user
        return updatedUser
    }
}