package com.nilenso.uncle.components

import com.nilenso.uncle.UncleServer
import com.nilenso.uncle.config.UncleConfig
import com.nilenso.uncle.modules.*
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
