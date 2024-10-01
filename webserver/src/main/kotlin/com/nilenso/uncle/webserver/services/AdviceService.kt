package com.nilenso.uncle.webserver.services

import com.nilenso.uncle.webserver.dto.AdviceDTO

interface AdviceService {
    suspend fun getAdvice(): AdviceDTO

    suspend fun addAdvice(advice: AdviceDTO): Boolean
    suspend fun goNap(uncleId: Int)
    suspend fun goWakeup(uncleId: Int)
}
