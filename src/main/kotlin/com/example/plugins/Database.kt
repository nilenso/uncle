package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabase(config: ApplicationConfig) {
    Database.connect(
        url = config.property("db.jdbcURL").getString(),
        user = config.property("db.user").getString(),
        password = config.property("db.password").getString()
    )
}