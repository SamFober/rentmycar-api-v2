package nl.avans.auth.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val accessToken: String,
    val refreshToken: String,
)
