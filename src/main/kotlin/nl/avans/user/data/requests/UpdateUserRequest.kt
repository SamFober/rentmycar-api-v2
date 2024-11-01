package nl.avans.user.data.requests

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val firstName: String? = null,
    val lastName: String? = null,
    val dateOfBirth: LocalDate? = null,
    val address: String? = null,
    val postalCode: String? = null,
    val city: String? = null,
    val phoneNumber: String? = null
)
