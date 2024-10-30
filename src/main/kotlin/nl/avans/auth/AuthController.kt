package nl.avans.auth

import at.favre.lib.crypto.bcrypt.BCrypt
import nl.avans.auth.data.requests.LoginRequest
import nl.avans.auth.data.responses.LoginResponse
import nl.avans.auth.utils.isValidEmail
import nl.avans.plugins.BadRequestException
import nl.avans.plugins.UnauthorizedException
import nl.avans.user.data.IUserRepository

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

            return LoginResponse(accessToken = accessToken, refreshToken = refreshToken)
        } else {
            throw UnauthorizedException("Incorrect email/password")
        }
    }
}