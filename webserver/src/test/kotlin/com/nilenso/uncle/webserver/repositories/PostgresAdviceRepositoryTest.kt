package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.testsuites.RepositoryTestSuite
import com.nilenso.uncle.webserver.dto.AdviceDTO
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test
import kotlin.test.assertEquals

class PostgresAdviceRepositoryTest {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setupDb(): Unit {
            println("start test")
        }
    }

    @Test
    fun addAdviceShouldAddAdvice() {
        val repository = PostgresAdviceRepository(Database.connect(RepositoryTestSuite.dataSource()))
        runBlocking {
            repository.addAdvice(AdviceDTO("This is just a test beta"))
            assertEquals("This is just a test beta", repository.getAdvice()?.advice, "Inserted advice not returned")
        }
    }
}