package nl.avans.user.data.response

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
    val userId: String,
    val createdAt: LocalDateTime,
    val firstName: String,
    val lastName: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val address: AddressResponse
)

@Serializable
data class AddressResponse(
    val address: String,
    val postalCode: String,
    val city: String,
    val phoneNumber: String
)

