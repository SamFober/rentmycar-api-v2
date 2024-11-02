package nl.avans.user

import kotlinx.datetime.toJavaLocalDate
import nl.avans.plugins.BadRequestException
import nl.avans.plugins.NotFoundException
import nl.avans.user.data.IUserRepository
import nl.avans.user.data.mappers.toUserResponse
import nl.avans.user.data.requests.UpdateUserRequest
import nl.avans.user.data.response.UserResponse
import nl.avans.user.domain.User
import nl.avans.user.domain.UserAddress
import java.util.*

class UserController(
    private val userRepository: IUserRepository
) {
    suspend fun updateUser(request: UpdateUserRequest, userId: UUID) {
        val existingUser = userRepository.findById(userId) ?: throw BadRequestException("Invalid user ID")

        userRepository.update(
            User(
                userId = userId,
                createdAt = existingUser.createdAt,
                firstName = request.firstName ?: existingUser.firstName,
                lastName = request.lastName ?: existingUser.lastName,
                email = existingUser.email,
                password = existingUser.password,
                dateOfBirth = request.dateOfBirth?.toJavaLocalDate() ?: existingUser.dateOfBirth,
                address = UserAddress(
                    address = request.address ?: existingUser.address.address,
                    city = request.city ?: existingUser.address.city,
                    postalCode = request.postalCode ?: existingUser.address.postalCode,
                    phoneNumber = request.phoneNumber ?: existingUser.address.phoneNumber
                )
            )
        )

    }

    suspend fun getUserById(userId: UUID): UserResponse {
        return userRepository.findById(userId)?.toUserResponse() ?: throw NotFoundException("User not found")
    }

    suspend fun deleteUser(userId: UUID) {
        if (!userRepository.delete(userId)) throw NotFoundException("User not found")
    }
}