package nl.avans.core.data.db.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.datetime

object UserTable : UUIDTable("users") {
    val createdAt = datetime("created_at")
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    val email = varchar("email_address", 128).uniqueIndex()
    val password = varchar("password", 255)
    val dateOfBirth = date("date_of_birth")
    val address = varchar("address", 255)
    val postalCode = varchar("postal_code", 10)
    val city = varchar("city", 100)
    val phoneNumber = varchar("phone_number", 20)
}