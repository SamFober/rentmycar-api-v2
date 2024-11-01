package nl.avans.di

import nl.avans.user.UserController
import org.koin.dsl.module

fun userModule() = module {
    single {
        UserController(get())
    }
}