package nl.avans.user.data.db

import nl.avans.core.data.db.tables.UserTable
import nl.avans.user.domain.User
import nl.avans.user.domain.UserAddress
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserDAO(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserDAO>(UserTable)

    var userId by UserTable.id
    var createdAt by UserTable.createdAt
    var firstName by UserTable.firstName
    var lastName by UserTable.lastName
    var email by UserTable.email
    var password by UserTable.password
    var dateOfBirth by UserTable.dateOfBirth
    var address by UserTable.address
    var postalCode by UserTable.postalCode
    var city by UserTable.city
    var phoneNumber by UserTable.phoneNumber
}

fun daoToUser(dao: UserDAO): User = User(
    userId = dao.userId.value,
    createdAt = dao.createdAt,
    firstName = dao.firstName,
    lastName = dao.lastName,
    email = dao.email,
    password = dao.password,
    dateOfBirth = dao.dateOfBirth,
    address = UserAddress(dao.address, dao.postalCode, dao.city, dao.phoneNumber)
)