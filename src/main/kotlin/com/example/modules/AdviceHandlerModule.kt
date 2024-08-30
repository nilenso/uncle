package com.example.modules

import com.example.handlers.AdviceHandler
import com.example.handlers.AdviceHandlerImpl
import com.example.repositories.AdviceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AdviceHandlerModule {

    companion object {
        @Singleton
        @Provides
        fun provideAdviceHandler(adviceRepository: AdviceRepository): AdviceHandler {
            return AdviceHandlerImpl(adviceRepository)
        }
    }
}