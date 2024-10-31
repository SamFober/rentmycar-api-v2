package nl.avans

import io.ktor.server.application.*
import nl.avans.plugins.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureHTTP()
    configureKoin()
    configureSerialization()
    configureStatusPages()
    configureDatabases()
    configureRouting()
}
