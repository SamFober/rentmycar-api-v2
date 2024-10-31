package nl.avans.user.data.mappers

import kotlinx.datetime.toJavaLocalDate
import nl.avans.auth.data.requests.RegisterRequest
import nl.avans.user.domain.User
import nl.avans.user.domain.UserAddress
import java.time.LocalDateTime


fun RegisterRequest.toUser(): User = User(
    createdAt = LocalDateTime.now(),
    firstName = firstName,
    lastName = lastName,
    email = email,
    password = password,
    dateOfBirth = dateOfBirth.toJavaLocalDate(),
    address = UserAddress(
        address = address,
        postalCode = postalCode,
        city = city,
        phoneNumber = phoneNumber
    )
)