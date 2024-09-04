package com.nilenso.uncle.webserver.services

import com.nilenso.uncle.webserver.entities.AdviceRecord
import com.nilenso.uncle.webserver.repositories.AdviceRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

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
            coEvery { adviceRepository.getAdvice() } returns AdviceRecord(EntityId<Int>).apply {
                advice = "Test advice"
            }
        runBlocking { coVerify { adviceRepository.getAdvice() } }
        val adviceDTO = runBlocking { adviceService.getAdvice() }
    }
}
