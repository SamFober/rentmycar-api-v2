package nl.avans.plugins

import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import nl.avans.core.data.db.tables.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    Database.connect(
        url = environment.config.property("db.url").getString(),
        user = environment.config.property("db.username").getString(),
        password = environment.config.property("db.password").getString(),
    )

    transaction {
        SchemaUtils.create(UserTable)
    }

}

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)
