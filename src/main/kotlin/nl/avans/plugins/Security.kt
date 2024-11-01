package nl.avans.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import nl.avans.auth.TokenManager
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
    // Please read the jwt property from the config file if you are using EngineMain
    val tokenManager: TokenManager by inject()

    val jwtAudience = environment.config.property("jwt.audience").getString()

    authentication {
        jwt {
            verifier(
                tokenManager.getTokenVerifier()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }
}
