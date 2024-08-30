package com.nilenso.uncle.repositories

import com.nilenso.uncle.dao.AdviceDAO

interface AdviceRepository {
    suspend fun getAdvice(): AdviceDAO
    suspend fun addAdvice(advice: AdviceDAO)
}