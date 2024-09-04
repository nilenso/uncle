package com.nilenso.uncle.webserver.services

import com.nilenso.uncle.webserver.dto.AdviceDTO

interface AdviceService {
    suspend fun getAdvice(): AdviceDTO

    suspend fun addAdvice(advice: AdviceDTO): Boolean
}