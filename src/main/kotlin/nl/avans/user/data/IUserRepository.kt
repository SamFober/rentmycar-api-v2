package nl.avans.user.data

import nl.avans.core.data.IBaseRepository
import nl.avans.user.domain.User

interface IUserRepository : IBaseRepository<User> {
    suspend fun findByEmail(email: String): User?
}