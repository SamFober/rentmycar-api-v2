package nl.avans.auth

import at.favre.lib.crypto.bcrypt.BCrypt
import kotlinx.datetime.toJavaLocalDate
import nl.avans.auth.data.requests.LoginRequest
import nl.avans.auth.data.requests.RegisterRequest
import nl.avans.auth.data.responses.LoginResponse
import nl.avans.auth.utils.*
import nl.avans.plugins.BadRequestException
import nl.avans.plugins.UnauthorizedException
import nl.avans.user.data.IUserRepository
import nl.avans.user.domain.User
import nl.avans.user.domain.UserAddress
import java.time.LocalDateTime

class AuthController(
    private val userRepository: IUserRepository,
    private val tokenManager: TokenManager
) {
    suspend fun login(request: LoginRequest): LoginResponse {

        if (!request.emailAddress.isValidEmail()) throw BadRequestException("Invalid email")

        val user =
            userRepository.findByEmail(request.emailAddress) ?: throw UnauthorizedException("Incorrect email/password")
        val passwordComparison = BCrypt.verifyer().verify(request.password.toCharArray(), user.password)

        if (passwordComparison.verified) {
            val accessToken = tokenManager.generateAccessToken(user)
            val refreshToken = tokenManager.generateRefreshToken(user)

            return LoginResponse(
                id = user.userId.toString(),
                firstName = user.firstName,
                lastName = user.lastName,
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        } else {
            throw UnauthorizedException("Incorrect email/password")
        }
    }

    suspend fun register(request: RegisterRequest) {
        if (!request.firstName.isValidName()) throw BadRequestException("Invalid name")
        if (!request.lastName.isValidName()) throw BadRequestException("Invalid name")
        if (!request.dateOfBirth.isOver18())
            throw BadRequestException("You must be at least 18 years old to create an account")
        if (!request.email.isValidEmail()) throw BadRequestException("Invalid email")
        if (!request.postalCode.isValidPostalCode()) throw BadRequestException("Invalid postal code")
        if (!request.phoneNumber.isValidPhoneNumber()) throw BadRequestException("Invalid phone number")

        userRepository.findByEmail(request.email)?.let {
            throw BadRequestException("User with email ${it.email} already exists")
        }

        val hashedPassword = BCrypt.withDefaults().hashToString(12, request.password.toCharArray())

        userRepository.save(
            User(
                createdAt = LocalDateTime.now(),
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                password = hashedPassword,
                dateOfBirth = request.dateOfBirth.toJavaLocalDate(),
                address = UserAddress(
                    address = request.address,
                    city = request.city,
                    postalCode = request.postalCode.uppercase(),
                    phoneNumber = request.phoneNumber
                )
            )
        )

    }
}