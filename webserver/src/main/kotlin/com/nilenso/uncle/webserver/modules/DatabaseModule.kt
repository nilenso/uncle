package com.nilenso.uncle.webserver.modules

import com.nilenso.uncle.webserver.config.UncleConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dagger.Module
import dagger.Provides
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import javax.inject.Singleton
import javax.sql.DataSource

@Module
interface DatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun providesHikariDataSource(uncleConfig: UncleConfig): DataSource {
            val dbConfig = uncleConfig.db
            val config = HikariConfig().apply {
                jdbcUrl = dbConfig.jdbcURL
                driverClassName = dbConfig.driverClassName
                username = dbConfig.username
                password = dbConfig.password
                maximumPoolSize = dbConfig.maxPoolSize
            }
            return HikariDataSource(config)
        }

        @Provides
        @Singleton
        fun providesDatabase(dataSource: DataSource): Database {
            return Database.connect(
                datasource = dataSource
            )
        }

        @Provides
        @Singleton
        fun providesFlyway(dataSource: DataSource): Flyway {
            return Flyway.configure()
                .dataSource(dataSource)
                .load()
        }

    }
}