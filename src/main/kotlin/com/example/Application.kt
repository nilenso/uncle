package com.example

import com.example.components.DaggerUncleComponent
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {

    embeddedServer(
        Netty,
        port = 8080,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    val uncleComponent = DaggerUncleComponent.create()
    val adviceHandler = uncleComponent.getAdviceHandler()

    configureSerialization()
    configureRouting(adviceHandler)
}
