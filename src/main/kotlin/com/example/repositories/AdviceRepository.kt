package com.example.repositories

import com.example.dao.AdviceDAO

interface AdviceRepository {
    suspend fun getAdvice(): AdviceDAO
    suspend fun addAdvice(advice: AdviceDAO)
}