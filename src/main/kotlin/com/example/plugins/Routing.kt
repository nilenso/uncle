package com.example.plugins

import com.example.repository.AdviceRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/health") {
            get {
                call.respond(HttpStatusCode.OK, "OK")
            }
        }

        route("/advice") {
            get {
                val advice = AdviceRepository.getAdvice()
                call.respondText(advice)
            }

            post {
                val formContent = call.receiveParameters()
                val advice = formContent["advice"]

                if (advice.isNullOrEmpty()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

                try {
                    AdviceRepository.addAdvice(advice)
                    call.respond(HttpStatusCode.NoContent)
                } catch (ex: Throwable) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}
