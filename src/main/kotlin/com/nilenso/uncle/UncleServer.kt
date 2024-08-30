package com.nilenso.uncle

import com.nilenso.uncle.components.DaggerUncleHandlers
import com.nilenso.uncle.config.UncleConfig
import com.nilenso.uncle.plugins.configureRouting
import com.nilenso.uncle.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.Logger
import javax.inject.Inject

class UncleServer @Inject constructor(val config: UncleConfig, private val log: Logger) {
    fun run() {
        log.info("Starting Server...")
        embeddedServer(
            Netty,
            port = config.server.port,
            module = Application::module
        ).start(wait = true)
    }
}

fun Application.module() {
    val uncleHandlers = DaggerUncleHandlers.create()
    val adviceHandler = uncleHandlers.getAdviceHandler()

    configureSerialization()
    configureRouting(adviceHandler)
}
