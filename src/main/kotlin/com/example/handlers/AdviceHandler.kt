package com.example.handlers

import com.example.dao.AdviceDAO

interface AdviceHandler {
   suspend fun getAdvice(): AdviceDAO
   suspend fun addAdvice(advice: AdviceDAO)
}