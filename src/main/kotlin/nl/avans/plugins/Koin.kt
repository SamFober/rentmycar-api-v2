package nl.avans.plugins

import io.ktor.server.application.*
import nl.avans.di.authModule
import nl.avans.di.commonModule
import nl.avans.di.userModule
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(commonModule(), authModule(this@configureKoin), userModule())
    }
}