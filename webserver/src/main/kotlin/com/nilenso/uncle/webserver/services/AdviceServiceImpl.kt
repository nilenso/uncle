package com.nilenso.uncle.webserver.services

import com.nilenso.uncle.webserver.dto.AdviceDTO
import com.nilenso.uncle.webserver.repositories.AdviceRepository
import jakarta.inject.Inject

class AdviceServiceImpl @Inject constructor(private val adviceRepository: AdviceRepository): AdviceService {
    override suspend fun getAdvice(): AdviceDTO {
        return AdviceDTO(adviceRepository.getAdvice()?.advice ?: "Uncle doesn't have any advice for you")
    }

    override suspend fun addAdvice(advice: AdviceDTO): Boolean {
        adviceRepository.addAdvice(advice)
        return true
    }
}