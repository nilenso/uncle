package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.dto.AdviceDTO
import kotlinx.coroutines.runBlocking
import com.nilenso.uncle.webserver.components.DaggerTestComponent
import com.nilenso.uncle.webserver.testsuites.RepositoryTestSuite
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class PostgresAdviceRepositoryTest {
    lateinit var repository: PostgresAdviceRepository

    @BeforeEach
    fun setupDb(): Unit {
        repository = RepositoryTestSuite.tc.getPostgresAdviceRepository()
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