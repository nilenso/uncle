package com.nilenso.uncle.repositories

import com.nilenso.uncle.dao.AdviceDAO
import com.nilenso.uncle.domain.Advice
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

class PostgresAdviceRepository @Inject constructor(private val db: Database) : AdviceRepository {
    override suspend fun getAdvice(): AdviceDAO {
        var adviceDAO: AdviceDAO? = null
        transaction {
            adviceDAO = Advice
                .all()
                .limit(1)
                .map { AdviceDAO(it.advice) }
                .firstOrNull()
        }
        return adviceDAO ?: AdviceDAO("Uncle doesn't have any advice for you")
    }

    override suspend fun addAdvice(advice: AdviceDAO) {
        transaction {
            Advice.new {
                this.advice = advice.advice
            }
        }
    }
}