package nl.avans.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import nl.avans.user.domain.User
import java.util.*

class TokenManager(
    private val jwtSecret: String,
    private val jwtIssuer: String,
    private val jwtAudience: String
) {
    constructor(
        application: Application
    ) : this(
        jwtSecret = application.environment.config.property("jwt.secret").getString(),
        jwtIssuer = application.environment.config.property("jwt.domain").getString(),
        jwtAudience = application.environment.config.property("jwt.audience").getString()
    )

    fun generateAccessToken(user: User): String {
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim("user_id", user.userId.toString())
            //Expiry date is 10 minutes from the current time
            .withExpiresAt(Date(System.currentTimeMillis() + 600_000))
            .sign(Algorithm.HMAC256(jwtSecret))
    }

    fun generateRefreshToken(user: User): String {
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim("user_id", user.userId.toString())
            .withClaim("token_id", System.currentTimeMillis())
            //Expiry date is one year from the current time
            .withExpiresAt(Date(System.currentTimeMillis() + 31_556_952_000))
            .sign(Algorithm.HMAC256(jwtSecret))
    }

    fun getTokenVerifier(): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(jwtSecret))
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .build()
    }
}