package com.nilenso.uncle.webserver.testsuites

import com.nilenso.uncle.webserver.containers.PostgresContainer
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.junit.platform.suite.api.*
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import java.time.Duration
import javax.sql.DataSource

@Suite
@SuiteDisplayName("Repository tests")
@SelectPackages("com.nilenso.uncle.webserver.repositories")
@IncludeClassNamePatterns(".*Test")
class RepositoryTestSuite {
    companion object {
        @Container
        private val pgContainer = PostgresContainer("postgres:16.0").withReuse(true)

        @JvmStatic
        @BeforeSuite
        fun setup() {
            pgContainer.start()
        }

        @JvmStatic
        @AfterSuite
        fun tearDown() {
            pgContainer.stop()
        }

        fun dataSource(): DataSource {
            if (!this::ds.isInitialized) {
                ds = HikariDataSource(HikariConfig().apply {
                    jdbcUrl = pgContainer.jdbcUrl
                    driverClassName = pgContainer.driverClassName
                    username = pgContainer.username
                    password = pgContainer.password
                    maximumPoolSize = 4
                })
            }
            return ds
        }
    }
}
