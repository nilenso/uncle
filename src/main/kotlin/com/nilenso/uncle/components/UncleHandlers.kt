package com.nilenso.uncle.components

import com.nilenso.uncle.handlers.AdviceHandler
import com.nilenso.uncle.modules.*
import dagger.Component
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
interface UncleHandlers {
    fun getAdviceHandler(): AdviceHandler

}