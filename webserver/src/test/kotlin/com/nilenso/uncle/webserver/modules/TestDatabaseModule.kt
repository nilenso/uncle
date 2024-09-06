package com.nilenso.uncle.webserver.modules

import dagger.Module
import dagger.Provides
import org.flywaydb.core.internal.database.base.Database
import org.testcontainers.containers.GenericContainer
import javax.inject.Singleton

@Module
interface TestDatabaseModule {
    companion object {
        @Provides
        @Singleton
        fun providesDatabaseContainer(): GenericContainer {
        }

        @Provides
        @Singleton
        fun providesDatabase(): Database {
        }
    }
}