package com.example.plugins

import com.example.repository.AdviceRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/health") {
            call.respond(HttpStatusCode.OK, "OK")
        }

        get("/advice") {
            val advice = AdviceRepository.getAdvice()
            call.respondText(advice)
        }
    }
}
