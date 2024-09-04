package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.dto.AdviceDTO

interface AdviceRepository {
    suspend fun getAdvice(): AdviceDTO
    suspend fun addAdvice(advice: AdviceDTO)
}