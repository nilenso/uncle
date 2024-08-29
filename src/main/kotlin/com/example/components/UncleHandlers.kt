package com.example.components

import com.example.handlers.AdviceHandler
import com.example.modules.AdviceHandlerModule
import com.example.modules.AdviceRepositoryModule
import com.example.modules.ConfigModule
import com.example.modules.DatabaseModule
import dagger.Component

@Component(
    modules = [
        AdviceHandlerModule::class,
        AdviceRepositoryModule::class,
        ConfigModule::class,
        DatabaseModule::class
    ]
)
interface UncleHandlers {
    fun getAdviceHandler(): AdviceHandler

}