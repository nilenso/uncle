package com.example.modules

import dagger.Provides
import dagger.Module
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Module
class LoggingModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger {
        return LoggerFactory.getLogger("Uncle")
    }
}