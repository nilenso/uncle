package com.nilenso.uncle.webserver.containers

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.testcontainers.containers.PostgreSQLContainer
import javax.sql.DataSource

class PostgresContainer : PostgreSQLContainer<PostgresContainer> {
    private lateinit var flyway: Flyway
    private lateinit var ds: DataSource

    constructor(dockerImageName: String) : super(dockerImageName)

    override fun start() {
        if (isRunning) {
            return
        }

        super.start()
        flyway = Flyway.configure()
            .dataSource(jdbcUrl, username, password)
            .cleanDisabled(false)
            .load()

        flyway.clean()
        flyway.migrate()
    }
    override fun stop() {
        flyway.clean()
        super.stop()
    }
    fun dataSource(): DataSource {
        if (!this::ds.isInitialized) {
            ds = HikariDataSource(HikariConfig().apply {
                jdbcUrl = jdbcUrl
                driverClassName = driverClassName
                username = username
                password = password
                maximumPoolSize = 4
            })
        }
        return ds
    }
}