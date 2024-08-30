package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.dao.AdviceDAO
import com.nilenso.uncle.webserver.domain.Advice
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class PostgresAdviceRepository @Inject constructor(private val db: Database) :
    com.nilenso.uncle.webserver.repositories.AdviceRepository {
    override suspend fun getAdvice(): AdviceDAO {
        var adviceDAO: AdviceDAO? = null
        transaction {
            adviceDAO = com.nilenso.uncle.webserver.domain.Advice
                .all()
                .limit(1)
                .map { AdviceDAO(it.advice) }
                .firstOrNull()
        }
        return adviceDAO ?: AdviceDAO("Uncle doesn't have any advice for you")
    }

    override suspend fun addAdvice(advice: AdviceDAO) {
        transaction {
            com.nilenso.uncle.webserver.domain.Advice.new {
                this.advice = advice.advice
            }
        }
    }
}