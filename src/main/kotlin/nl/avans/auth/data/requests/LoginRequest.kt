package nl.avans.auth.data.requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val emailAddress: String,
    val password: String
)
