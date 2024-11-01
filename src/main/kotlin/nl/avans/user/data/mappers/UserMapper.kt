package nl.avans.user.data.mappers


import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import nl.avans.user.data.response.AddressResponse
import nl.avans.user.data.response.UserResponse
import nl.avans.user.domain.User


fun User.toUserResponse() = UserResponse(
    userId = this.userId!!.toString(),
    createdAt = LocalDateTime(
        this.createdAt.year,
        this.createdAt.month,
        this.createdAt.dayOfMonth,
        this.createdAt.hour,
        this.createdAt.minute,
        this.createdAt.second
    ),
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    dateOfBirth = LocalDate(
        this.dateOfBirth.year,
        this.dateOfBirth.month,
        this.dateOfBirth.dayOfMonth
    ),
    address = AddressResponse(
        address = this.address.address,
        postalCode = this.address.postalCode,
        city = this.address.city,
        phoneNumber = this.address.phoneNumber
    )
)