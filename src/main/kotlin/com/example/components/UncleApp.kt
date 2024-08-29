package com.example.components

import com.example.UncleServer
import com.example.config.UncleConfig
import com.example.handlers.AdviceHandler
import com.example.modules.AdviceHandlerModule
import com.example.modules.AdviceRepositoryModule
import com.example.modules.ConfigModule
import com.example.modules.DatabaseModule
import dagger.Component
import org.flywaydb.core.Flyway

@Component(
    modules = [
        AdviceHandlerModule::class,
        AdviceRepositoryModule::class,
        ConfigModule::class,
        DatabaseModule::class
    ]
)
interface UncleApp {
    fun server(): UncleServer

    fun getConfig(): UncleConfig

    fun flyway(): Flyway

}
