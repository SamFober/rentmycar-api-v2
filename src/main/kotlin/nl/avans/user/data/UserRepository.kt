package nl.avans.user.data

import nl.avans.core.data.db.tables.UserTable
import nl.avans.plugins.suspendTransaction
import nl.avans.user.data.db.UserDAO
import nl.avans.user.data.db.daoToUser
import nl.avans.user.domain.User
import java.util.*

class UserRepository : IUserRepository {
    override suspend fun findByEmail(email: String): User? = suspendTransaction {
        UserDAO
            .find { UserTable.email eq email }
            .limit(1)
            .map(::daoToUser)
            .firstOrNull()
    }

    override suspend fun findAll(): List<User> = suspendTransaction {
        UserDAO.all().map(::daoToUser)
    }

    override suspend fun findById(id: UUID): User? = suspendTransaction {
        UserDAO.findById(id)?.let { daoToUser(it) }
    }

    override suspend fun save(entity: User): Unit = suspendTransaction {
        UserDAO.new {
            createdAt = entity.createdAt
            firstName = entity.firstName
            lastName = entity.lastName
            email = entity.email
            password = entity.password
            dateOfBirth = entity.dateOfBirth
            address = entity.address.address
            postalCode = entity.address.postalCode
            city = entity.address.city
            phoneNumber = entity.address.phoneNumber
        }
    }

    override suspend fun update(entity: User): Boolean = suspendTransaction {
        UserDAO.findByIdAndUpdate(entity.userId) {
            it.firstName = entity.firstName
            it.lastName = entity.lastName
            it.email = entity.email
            it.password = entity.password
            it.dateOfBirth = entity.dateOfBirth
            it.address = entity.address.address
            it.postalCode = entity.address.postalCode
            it.city = entity.address.city
            it.phoneNumber = entity.address.phoneNumber
        } != null
    }

    override suspend fun delete(id: UUID): Boolean = suspendTransaction {
        UserDAO.findById(id)?.let {
            it.delete()
            true
        }
        false
    }

}