package com.nilenso.uncle.webserver.plugins

import com.nilenso.uncle.webserver.dao.AdviceDAO
import com.nilenso.uncle.webserver.handlers.AdviceHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(adviceHandler: AdviceHandler) {
    routing {
        route("/health") {
            get {
                call.respond(HttpStatusCode.OK, "OK")
            }
        }

        route("/advice") {
            get { adviceHandler.getAdvice(call) }
            post { adviceHandler.addAdvice(call) }
        }
    }
}
