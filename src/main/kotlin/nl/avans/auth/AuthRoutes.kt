package nl.avans.auth

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nl.avans.auth.data.requests.LoginRequest
import nl.avans.auth.data.requests.RegisterRequest

fun Route.authRoutes(controller: AuthController) {
    route("/auth") {
        post("/login") {
            val request = call.receive<LoginRequest>()
            call.respond(HttpStatusCode.OK, controller.login(request))
        }

        post("/register") {
            val request = call.receive<RegisterRequest>()
            call.respond(HttpStatusCode.Created, controller.register(request))
        }
    }
}