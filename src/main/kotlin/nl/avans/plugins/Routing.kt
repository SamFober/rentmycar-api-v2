package nl.avans.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import nl.avans.auth.AuthController
import nl.avans.auth.authRoutes
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val authController: AuthController by inject()

    routing {
        authRoutes(authController)
    }
}
