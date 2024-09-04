package com.nilenso.uncle.webserver.handlers

import com.nilenso.uncle.webserver.dto.AdviceDTO
import com.nilenso.uncle.webserver.repositories.AdviceRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import com.nilenso.uncle.webserver.services.AdviceService
import javax.inject.Inject

class AdviceHandlerImpl @Inject constructor(private val adviceService: AdviceService) :
    com.nilenso.uncle.webserver.handlers.AdviceHandler {
    override suspend fun getAdvice(call: ApplicationCall) {
        call.respond(adviceService.getAdvice())
    }

    override suspend fun addAdvice(call: ApplicationCall) {
        try {
            val adviceReq = call.receive<AdviceDTO>()

            if (adviceReq.advice.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest)
                return
            }

            if (!adviceService.addAdvice(adviceReq)) {
                throw Exception("Failed to add advice ${adviceReq.advice}")
            }
            call.respond(HttpStatusCode.NoContent)
        } catch (ex: Throwable) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}