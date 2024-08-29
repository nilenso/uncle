package com.example.plugins

import com.example.dao.AdviceDAO
import com.example.domain.Advice
import com.example.repository.AdviceRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(adviceRepository: AdviceRepository) {
    routing {
        route("/health") {
            get {
                call.respond(HttpStatusCode.OK, "OK")
            }
        }

        route("/advice") {
            get {
                val advice = adviceRepository.getAdvice()
                call.respond(advice)
            }

            post {
                try {
                    val advice = call.receive<AdviceDAO>()

                    if (advice.advice.isEmpty()) {
                        call.respond(HttpStatusCode.BadRequest)
                        return@post
                    }

                    adviceRepository.addAdvice(advice)
                    call.respond(HttpStatusCode.NoContent)
                } catch (ex: Throwable) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}
