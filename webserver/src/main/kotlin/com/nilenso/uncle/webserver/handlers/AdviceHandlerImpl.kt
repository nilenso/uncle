package com.nilenso.uncle.webserver.handlers

import com.nilenso.uncle.webserver.dao.AdviceDAO
import com.nilenso.uncle.webserver.repositories.AdviceRepository
import javax.inject.Inject

class AdviceHandlerImpl @Inject constructor(private val adviceRepository: AdviceRepository) :
    com.nilenso.uncle.webserver.handlers.AdviceHandler {
    override suspend fun getAdvice(): AdviceDAO {
        return adviceRepository.getAdvice()
    }

    override suspend fun addAdvice(advice: AdviceDAO) {
        return adviceRepository.addAdvice(advice)
    }
}