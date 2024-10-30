package nl.avans.user.domain

data class UserAddress(
    val address: String,
    val postalCode: String,
    val city: String,
    val phoneNumber: String
)
