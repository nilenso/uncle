package com.example

import com.example.plugins.*
import com.example.repository.PostgresAdviceRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val adviceRepository = PostgresAdviceRepository()

    configureSerialization()
    configureDatabase(environment.config)
    configureRouting(adviceRepository)
}
