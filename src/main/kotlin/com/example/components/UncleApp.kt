package com.example.components

import com.example.UncleServer
import com.example.config.UncleConfig
import com.example.modules.*
import dagger.Component
import org.flywaydb.core.Flyway
import javax.inject.Singleton

@Component(
    modules = [
        AdviceHandlerModule::class,
        AdviceRepositoryModule::class,
        ConfigModule::class,
        DatabaseModule::class,
        LoggingModule::class
    ]
)
@Singleton
interface UncleApp {
    fun server(): UncleServer

    fun getConfig(): UncleConfig

    fun flyway(): Flyway

}
