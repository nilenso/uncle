package com.nilenso.uncle.webserver.modules

import com.nilenso.uncle.webserver.containers.PostgresContainer
import com.nilenso.uncle.webserver.repositories.PostgresAdviceRepository
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dagger.Module
import dagger.Provides
import org.jetbrains.exposed.sql.Database
import javax.inject.Singleton

@Module
interface TestDatabaseModule {
    companion object {
        @Provides
        @Singleton
        fun providesDatabaseContainer(): PostgresContainer {
            val container = PostgresContainer("postgres:16.0").withReuse(true)
            container.start()

            return container
        }

        @Provides
        @Singleton
        fun providesDatabase(container: PostgresContainer): Database {
            return Database.connect(HikariDataSource(HikariConfig().apply {
                jdbcUrl = container.jdbcUrl
                driverClassName = container.driverClassName
                username = container.username
                password = container.password
                maximumPoolSize = 4
            }))
        }

        @Provides
        @Singleton
        fun providesPostgresAdviceRepository(database: Database): PostgresAdviceRepository {
            return PostgresAdviceRepository(database)
        }
    }
}