package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.testsuites.RepositoryTestSuite
import com.nilenso.uncle.webserver.dto.AdviceDTO
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class PostgresAdviceRepositoryTest {
    lateinit var repository: PostgresAdviceRepository

    @BeforeEach
    fun setupDb(): Unit {
        repository = PostgresAdviceRepository(Database.connect(RepositoryTestSuite.dataSource()))
        println("start test")
    }
    @Test
    fun addAdviceShouldAddAdvice() {
        runBlocking {
            repository.addAdvice(AdviceDTO("This is just a test beta"))
            assertEquals("This is just a test beta", repository.getAdvice()?.advice, "Inserted advice not returned")
        }
    }
}