package com.example.repository

import com.example.dao.AdviceDAO
import com.example.domain.Advice

interface AdviceRepository {
    suspend fun getAdvice(): AdviceDAO
    suspend fun addAdvice(advice: AdviceDAO)
}