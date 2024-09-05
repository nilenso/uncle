package com.nilenso.uncle.webserver.repositories

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
        private val pgContainer = PostgreSQLContainer("postgres:16.0").withReuse(true)
        private lateinit var flyway: Flyway
        private lateinit var ds: DataSource
        private var testDuration: Long = 0

        @JvmStatic
        @BeforeSuite
        fun setup() {
            testDuration = System.nanoTime()
            if (pgContainer.isRunning) {
                return
            }

            pgContainer.start()
            flyway = Flyway.configure()
                .dataSource(pgContainer.jdbcUrl, pgContainer.username, pgContainer.password)
                .cleanDisabled(false)
                .load()

            flyway.clean()
            flyway.migrate()
        }

        @JvmStatic
        @AfterSuite
        fun tearDown() {
            println("AFTER SUITE")
            flyway.clean()
            pgContainer.stop()
            testDuration = System.nanoTime() - testDuration
            println("Duration: ${Duration.ofNanos(testDuration).toMillis()}")
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