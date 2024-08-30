package com.nilenso.uncle.webserver.components

import com.nilenso.uncle.webserver.UncleServer
import com.nilenso.uncle.webserver.config.UncleConfig
import com.nilenso.uncle.webserver.modules.*
import dagger.Component
import org.flywaydb.core.Flyway
import javax.inject.Singleton

@Component(
    modules = [
        AdviceHandlerModule::class,
        AdviceRepositoryModule::class,
        com.nilenso.uncle.webserver.modules.ConfigModule::class,
        com.nilenso.uncle.webserver.modules.DatabaseModule::class,
        LoggingModule::class
    ]
)
@Singleton
interface UncleApp {
    fun server(): UncleServer

    fun getConfig(): UncleConfig

    fun flyway(): Flyway

}
