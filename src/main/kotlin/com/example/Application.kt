package com.example

import com.example.plugins.*
import com.example.repository.AdviceRepositoryImpl
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val adviceRepository = AdviceRepositoryImpl()

    configureSerialization()
    configureRouting(adviceRepository)
}
