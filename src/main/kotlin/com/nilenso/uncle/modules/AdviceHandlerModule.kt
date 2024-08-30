package com.nilenso.uncle.modules

import com.nilenso.uncle.handlers.AdviceHandler
import com.nilenso.uncle.handlers.AdviceHandlerImpl
import com.nilenso.uncle.repositories.AdviceRepository
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