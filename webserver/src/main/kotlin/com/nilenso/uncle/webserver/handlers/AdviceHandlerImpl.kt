package com.nilenso.uncle.webserver.handlers

import com.nilenso.uncle.webserver.dao.AdviceDAO
import com.nilenso.uncle.webserver.repositories.AdviceRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import javax.inject.Inject

class AdviceHandlerImpl @Inject constructor(private val adviceRepository: AdviceRepository) :
    com.nilenso.uncle.webserver.handlers.AdviceHandler {
    override suspend fun getAdvice(call: ApplicationCall) {
        call.respond(adviceRepository.getAdvice())
    }

    override suspend fun addAdvice(call: ApplicationCall) {
        try {
            val adviceReq = call.receive<AdviceDAO>()

            if (adviceReq.advice.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest)
                return
            }

            val advice = adviceRepository.addAdvice(adviceReq)
            call.respond(HttpStatusCode.NoContent)
        } catch (ex: Throwable) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}