package com.example.modules

import com.example.config.UncleConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dagger.Module
import dagger.Provides
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

@Module
interface DatabaseModule {

    companion object {
        @Provides
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
        fun providesDatabase(dataSource: DataSource): Database {
            return Database.connect(
                datasource = dataSource
            )
        }
    }
}