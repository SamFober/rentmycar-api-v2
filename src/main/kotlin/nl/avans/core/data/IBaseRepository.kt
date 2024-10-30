package nl.avans.core.data

import java.util.*

interface IBaseRepository<T> {
    suspend fun findAll(): List<T>
    suspend fun findById(id: UUID): T?
    suspend fun save(entity: T)
    suspend fun update(entity: T): Boolean
    suspend fun delete(id: UUID): Boolean
}