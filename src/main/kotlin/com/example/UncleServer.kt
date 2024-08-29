package com.example

import com.example.components.DaggerUncleHandlers
import com.example.config.UncleConfig
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import javax.inject.Inject

class UncleServer @Inject constructor(val config: UncleConfig) {
    fun run() {
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
