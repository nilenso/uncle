package com.example.repository

import com.example.dao.Advice

interface AdviceRepository {
    suspend fun getAdvice(): Advice
    suspend fun addAdvice(advice: Advice)
}