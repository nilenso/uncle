package com.nilenso.uncle.webserver.services

import com.nilenso.uncle.webserver.domain.Advice
import com.nilenso.uncle.webserver.repositories.AdviceRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class AdviceRecordServiceImplTest {
    private lateinit var adviceRepository: AdviceRepository
    private lateinit var adviceService: AdviceServiceImpl

    @BeforeEach
    fun setup() {
        adviceRepository = mockk()
        adviceService = AdviceServiceImpl(adviceRepository)
    }

    @Test
    fun getAdviceShouldCallGetAdviceMethodFromRepository() {
        val expectedAdvice = Advice("Test Advice")
        coEvery { adviceRepository.getAdvice() } returns expectedAdvice
        val adviceDTO = runBlocking {  adviceService.getAdvice() }
        assertEquals(adviceDTO.advice, "Test Advice")
        coVerify { adviceRepository.getAdvice() }
    }

    @Test
    fun getAdviceShouldReturnAStaticStringIfThereIsNoAdviceInDB() {
        coEvery { adviceRepository.getAdvice() } returns null
        val adviceDTO = runBlocking { adviceService.getAdvice() }

        coVerify { adviceRepository.getAdvice() }
        assertEquals(adviceDTO.advice, "Uncle doesn't have any advice for you")
    }
}
