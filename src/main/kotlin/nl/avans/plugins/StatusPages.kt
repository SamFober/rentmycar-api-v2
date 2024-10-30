package nl.avans.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<io.ktor.server.plugins.BadRequestException> { call, cause ->
            call.respondText(text = "400: Bad request", status = HttpStatusCode.BadRequest)
        }
        exception<BadRequestException> { call, cause ->
            call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
        }
        exception<UnauthorizedException> { call, cause ->
            call.respondText(text = "401: ${cause.message}", status = HttpStatusCode.Unauthorized)
        }
        exception<NotFoundException> { call, cause ->
            call.respondText(text = "404: ${cause.message}", status = HttpStatusCode.NotFound)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: ${cause.message}", status = HttpStatusCode.InternalServerError)
        }
    }
}

class UnauthorizedException(override val message: String = "Unauthorized") : Exception(message)
class NotFoundException(override val message: String = "Resource not found") : Exception(message)
class BadRequestException(override val message: String = "Bad request") : Exception(message)