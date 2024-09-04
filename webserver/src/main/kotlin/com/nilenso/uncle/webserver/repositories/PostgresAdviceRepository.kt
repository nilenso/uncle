package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.domain.Advice
import com.nilenso.uncle.webserver.entities.AdviceRecord
import com.nilenso.uncle.webserver.entities.AdviceTable
import com.nilenso.uncle.webserver.dto.AdviceDTO
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Random
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class PostgresAdviceRepository @Inject constructor(private val db: Database) :
    com.nilenso.uncle.webserver.repositories.AdviceRepository {
    override suspend fun getAdvice(): Advice? {
        var advice: Advice? = null
        transaction {
            advice = AdviceTable
                .selectAll()
                .orderBy(Random())
                .limit(1)
                .map { row -> Advice(row[AdviceTable.adviceText]) }
                .firstOrNull()
        }
        return advice
    }

    override suspend fun addAdvice(advice: AdviceDTO) {
        transaction {
            AdviceRecord.new {
                this.advice = advice.advice
            }
        }
    }
}