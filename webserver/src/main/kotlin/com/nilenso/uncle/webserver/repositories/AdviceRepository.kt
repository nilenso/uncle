package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.dao.AdviceDAO

interface AdviceRepository {
    suspend fun getAdvice(): AdviceDAO
    suspend fun addAdvice(advice: AdviceDAO)
}