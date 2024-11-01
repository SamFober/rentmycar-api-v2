package nl.avans

import io.ktor.server.application.*
import nl.avans.plugins.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    //Koin NEEDS to start first because we need the dependencies for the remaining plugins.
    configureKoin()
    configureSecurity()
    configureHTTP()
    configureSerialization()
    configureStatusPages()
    configureDatabases()
    configureRouting()
}
