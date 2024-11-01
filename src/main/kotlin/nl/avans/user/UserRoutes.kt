package nl.avans.user

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nl.avans.user.data.requests.UpdateUserRequest
import java.util.*

fun Route.userRoutes(controller: UserController) {
    route("/user") {
        authenticate {
            put {
                val userId = call.principal<JWTPrincipal>()!!.payload.getClaim("user_id").asString()
                val request = call.receive<UpdateUserRequest>()

                call.respond(HttpStatusCode.OK, controller.updateUser(request, UUID.fromString(userId)))
            }
        }
        get("/{userId}") {
            val id = call.parameters["userId"] ?: call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
            call.respond(HttpStatusCode.OK, controller.getUserById(UUID.fromString(id.toString())))
        }
    }
}