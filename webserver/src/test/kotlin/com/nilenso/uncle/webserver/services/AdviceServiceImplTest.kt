package com.nilenso.uncle.webserver.services

import com.nilenso.uncle.webserver.domain.Advice
import com.nilenso.uncle.webserver.repositories.AdviceRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AdviceServiceImplTest {
    private val adviceRepository: AdviceRepository = mockk()
    private val adviceService = AdviceServiceImpl(adviceRepository)

    @BeforeEach
    fun setup() {
    }

    @Test
    fun getAdviceShouldCallGetAdviceMethodFromRepository() {
            coEvery { adviceRepository.getAdvice() } returns Advice.new(1) {
                advice = "Test advice"
            }
        runBlocking { coVerify { adviceRepository.getAdvice() } }
        val adviceDTO = adviceService.getAdvice()
    }
}
