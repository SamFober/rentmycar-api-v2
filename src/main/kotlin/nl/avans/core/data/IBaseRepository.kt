package nl.avans.core.data

import java.util.*

interface IBaseRepository<T> {
    suspend fun findAll(): List<T>
    suspend fun findById(id: UUID): T?
    suspend fun save(t: T)
    suspend fun update(t: T)
    suspend fun delete(id: UUID)
}