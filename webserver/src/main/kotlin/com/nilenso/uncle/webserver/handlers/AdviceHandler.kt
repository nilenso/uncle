package com.nilenso.uncle.webserver.handlers

import com.nilenso.uncle.webserver.dao.AdviceDAO

interface AdviceHandler {
   suspend fun getAdvice(): AdviceDAO
   suspend fun addAdvice(advice: AdviceDAO)
}