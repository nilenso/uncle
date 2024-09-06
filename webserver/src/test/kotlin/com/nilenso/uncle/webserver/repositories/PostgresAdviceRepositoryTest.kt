package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.components.TestComponent
import com.nilenso.uncle.webserver.containers.PostgresContainer
import com.nilenso.uncle.webserver.dto.AdviceDTO
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(TestComponent.Extension::class)
class PostgresAdviceRepositoryTest(tc: TestComponent) {

    private val repository: PostgresAdviceRepository = tc.getPostgresAdviceRepository()
    @BeforeEach
    fun setupDb(): Unit {
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