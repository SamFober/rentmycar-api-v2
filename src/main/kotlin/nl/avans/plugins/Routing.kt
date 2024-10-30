package nl.avans.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import nl.avans.auth.AuthController
import nl.avans.auth.TokenManager
import nl.avans.auth.authRoutes
import nl.avans.user.data.UserRepository

fun Application.configureRouting() {
    routing {
        authRoutes(AuthController(UserRepository(), TokenManager(this@configureRouting)))
    }
}
