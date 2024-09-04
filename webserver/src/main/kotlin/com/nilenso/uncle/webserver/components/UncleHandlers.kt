package com.nilenso.uncle.webserver.components

import com.nilenso.uncle.webserver.handlers.AdviceHandler
import com.nilenso.uncle.webserver.modules.*
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AdviceHandlerModule::class,
        AdviceServiceModule::class,
        AdviceRepositoryModule::class,
        com.nilenso.uncle.webserver.modules.ConfigModule::class,
        com.nilenso.uncle.webserver.modules.DatabaseModule::class,
        LoggingModule::class
    ]
)
@Singleton
interface UncleHandlers {
    fun getAdviceHandler(): com.nilenso.uncle.webserver.handlers.AdviceHandler

}