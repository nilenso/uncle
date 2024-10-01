package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.domain.Advice
import com.nilenso.uncle.webserver.entities.AdviceRecord
import com.nilenso.uncle.webserver.entities.AdviceTable
import com.nilenso.uncle.webserver.dto.AdviceDTO
import com.nilenso.uncle.webserver.entities.NapTable
import com.nilenso.uncle.webserver.entities.UncleTable
import com.zaxxer.hikari.util.IsolationLevel
import kotlinx.coroutines.delay
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Random
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.Logger
import org.slf4j.MDC
import java.sql.Connection
import java.util.*
import javax.inject.Inject
import kotlin.math.absoluteValue

class PostgresAdviceRepository @Inject constructor(private val db: Database, val log: Logger) :
    com.nilenso.uncle.webserver.repositories.AdviceRepository {
    companion object {
        var rid = 0

        fun reqid(): Int {
            rid = rid+1
            return rid
        }
    }

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

    override suspend fun goNap(uncleId: Int) {
        MDC.put("rid", reqid().toString())
        log.info("Starting txn")
        transaction(transactionIsolation = Connection.TRANSACTION_REPEATABLE_READ) {
            val uncle = UncleTable.selectAll().where({ UncleTable.id eq uncleId }).first()
            MDC.put("nap", uncle[UncleTable.napId].toString())

            log.info("selected uncle")
            if (uncle[UncleTable.status] == 1) {
                log.info("Already napping")
            } else {
                log.info("inserting nap")
                val newNapId = NapTable.insertReturning {
                    it[status] = 1
                    it[NapTable.uncleId] = uncleId
                }.single()[NapTable.id]
                MDC.put("newnap", newNapId.value.toString())

                log.info("sleeping nap")
                Thread.sleep(longArrayOf(500 ,1000).random())
                log.info("Updating uncle")

                UncleTable.update({ (UncleTable.id eq uncleId) and (UncleTable.napId eq uncle[UncleTable.napId]) }) {
                    it[status] = 1
                    it[napId] = newNapId.value
                }
                log.info("Updated uncle")
            }
        }
        log.info("Txn committed")
    }

    override suspend fun goWakeup(uncleId: Int) {
        MDC.put("rid", reqid().toString())
        log.info("Starting txn")
        transaction {
            val uncle = UncleTable.selectAll().where({ UncleTable.id eq uncleId }).first()

            log.info("selected uncle")
            if (uncle[UncleTable.status] == 0) {
                log.info("Already napping")
            } else {
                log.info("Updating uncle")

                UncleTable.update({ UncleTable.id eq uncleId }) {
                    it[status] = 0
                }
                log.info("Updated uncle")
                Thread.sleep(longArrayOf(500 ,1000).random())
                NapTable.update({ NapTable.id eq uncle[UncleTable.napId] }) {
                    it[status] = 0
                }
                log.info("Updated nap")

            }
        }
    }
}
