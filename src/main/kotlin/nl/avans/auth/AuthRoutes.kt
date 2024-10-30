package nl.avans.auth

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nl.avans.auth.data.requests.LoginRequest

fun Route.authRoutes(controller: AuthController) {
    route("/auth") {
        post("/login") {
            val request = call.receive<LoginRequest>()
            call.respond(HttpStatusCode.OK, controller.login(request))
        }
    }
}