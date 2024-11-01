package nl.avans.di

import io.ktor.server.application.*
import nl.avans.auth.AuthController
import nl.avans.auth.TokenManager
import org.koin.dsl.module

fun authModule(application: Application) = module {
    single {
        TokenManager(application)
    }

    single {
        AuthController(get(), get())
    }
}