package nl.avans.auth.data.requests

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val dateOfBirth: LocalDate,
    val email: String,
    val password: String,
    val address: String,
    val postalCode: String,
    val city: String,
    val phoneNumber: String
)
