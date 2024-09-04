package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.dto.AdviceDTO
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class PostgresAdviceRepository @Inject constructor(private val db: Database) :
    com.nilenso.uncle.webserver.repositories.AdviceRepository {
    override suspend fun getAdvice(): AdviceDTO {
        var adviceDTO: AdviceDTO? = null
        transaction {
            adviceDTO = com.nilenso.uncle.webserver.domain.Advice
                .all()
                .limit(1)
                .map { AdviceDTO(it.advice) }
                .firstOrNull()
        }
        return adviceDTO ?: AdviceDTO("Uncle doesn't have any advice for you")
    }

    override suspend fun addAdvice(advice: AdviceDTO) {
        transaction {
            com.nilenso.uncle.webserver.domain.Advice.new {
                this.advice = advice.advice
            }
        }
    }
}