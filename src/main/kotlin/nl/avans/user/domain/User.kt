package nl.avans.user.domain

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


data class User(
    val userId: UUID? = null,
    val createdAt: LocalDateTime,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val dateOfBirth: LocalDate,
    val address: UserAddress
)

enum class Role {
    USER,
    ADMIN
}
