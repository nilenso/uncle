package com.example.components

import com.example.handlers.AdviceHandler
import com.example.modules.*
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