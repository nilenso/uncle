package com.nilenso.uncle.webserver.handlers

import com.nilenso.uncle.webserver.dto.AdviceDTO
import com.nilenso.uncle.webserver.repositories.AdviceRepository
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.BeforeEach
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class AdviceHandlerImplTest {

    private lateinit var adviceRepository: AdviceRepository
    private lateinit var adviceHandler: AdviceHandlerImpl

    @BeforeEach
    fun setup() {
        adviceRepository = mockk()
        adviceHandler = AdviceHandlerImpl(adviceRepository)
    }

    @Test
    fun getAdviceShouldReturnAdviceUsingRepository() = runBlocking {
        val expectedAdvice = AdviceDTO("Uncle's sage advice")
        coEvery { adviceRepository.getAdvice() } returns expectedAdvice

        val result = adviceHandler.getAdvice()

        coVerify { adviceRepository.getAdvice() }
        assertThat(result, `is`(equalTo(expectedAdvice)))
    }

    @Test
    fun addAdviceShouldAddAdviceUsingRepository() = runBlocking {
        val adviceToAdd = AdviceDTO("New advice")
        coJustRun { adviceRepository.addAdvice(any()) }

        adviceHandler.addAdvice(adviceToAdd)

        coVerify { adviceRepository.addAdvice(adviceToAdd) }
    }
}