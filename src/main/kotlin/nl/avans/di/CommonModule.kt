package nl.avans.di

import nl.avans.user.data.IUserRepository
import nl.avans.user.data.UserRepository
import org.koin.dsl.module

fun commonModule() = module {
    single<IUserRepository> {
        UserRepository()
    }
}