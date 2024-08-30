package com.nilenso.uncle.handlers

import com.nilenso.uncle.dao.AdviceDAO

interface AdviceHandler {
   suspend fun getAdvice(): AdviceDAO
   suspend fun addAdvice(advice: AdviceDAO)
}