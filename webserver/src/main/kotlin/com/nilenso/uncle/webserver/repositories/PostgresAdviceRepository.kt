package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.domain.AdviceTable
import com.nilenso.uncle.webserver.dto.AdviceDTO
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Random
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class PostgresAdviceRepository @Inject constructor(private val db: Database) :
    com.nilenso.uncle.webserver.repositories.AdviceRepository {
    override suspend fun getAdvice(): AdviceDTO {
        var adviceDTO: AdviceDTO? = null
        transaction {
            adviceDTO = AdviceTable
                .selectAll()
                .orderBy(Random())
                .limit(1)
                .map { AdviceDTO(it[AdviceTable.adviceText]) }
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