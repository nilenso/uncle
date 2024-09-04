package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.domain.Advice
import com.nilenso.uncle.webserver.dto.AdviceDTO

interface AdviceRepository {
    suspend fun getAdvice(): Advice?
    suspend fun addAdvice(advice: AdviceDTO)
}