package com.example.handlers

import com.example.dao.AdviceDAO
import com.example.repositories.AdviceRepository
import javax.inject.Inject

class AdviceHandlerImpl @Inject constructor(private val adviceRepository: AdviceRepository) : AdviceHandler {
    override suspend fun getAdvice(): AdviceDAO {
        return adviceRepository.getAdvice()
    }

    override suspend fun addAdvice(advice: AdviceDAO) {
        return adviceRepository.addAdvice(advice)
    }
}