package com.nilenso.uncle.webserver.modules

import com.nilenso.uncle.webserver.handlers.AdviceHandler
import com.nilenso.uncle.webserver.handlers.AdviceHandlerImpl
import com.nilenso.uncle.webserver.repositories.AdviceRepository
import com.nilenso.uncle.webserver.services.AdviceService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AdviceHandlerModule {

    companion object {
        @Singleton
        @Provides
        fun provideAdviceHandler(adviceService: AdviceService): com.nilenso.uncle.webserver.handlers.AdviceHandler {
            return com.nilenso.uncle.webserver.handlers.AdviceHandlerImpl(adviceService)
        }
    }
}