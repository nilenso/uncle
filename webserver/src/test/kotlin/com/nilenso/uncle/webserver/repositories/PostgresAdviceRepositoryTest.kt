package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.config.DBConfig
import com.nilenso.uncle.webserver.dto.AdviceDTO
import kotlinx.coroutines.runBlocking
import org.flywaydb.core.Flyway
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.`is`
import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PostgresAdviceRepositoryTest {

    private lateinit var database: Database
    private lateinit var repository: PostgresAdviceRepository
    private lateinit var flyway: Flyway

    @BeforeEach
    fun setup() {
        val dbConfig = DBConfig(
            driverClassName = "org.postgresql.Driver",
            jdbcURL = "jdbc:postgresql://localhost:5433/uncletestdb",
            username = "testuser",
            password = "testpassword",
            maxPoolSize = 5
        )

        database = Database.connect(
            url = dbConfig.jdbcURL,
            driver = dbConfig.driverClassName,
            user = dbConfig.username,
            password = dbConfig.password
        )

        flyway = Flyway.configure()
            .dataSource(dbConfig.jdbcURL, dbConfig.username, dbConfig.password)
            .cleanDisabled(false)
            .load()

        flyway.clean()
        flyway.migrate()

        repository = PostgresAdviceRepository(database)

    }

    @AfterEach
    fun tearDown() {
        flyway.clean()
    }

    @Test
    fun addAdviceShouldStoreAdviceInDB() = runBlocking {
        val adviceToAdd = AdviceDTO("New sage advice")

        repository.addAdvice(adviceToAdd)

        val retrievedAdvice = repository.getAdvice()
        assertThat(retrievedAdvice.advice, `is`(equalTo(adviceToAdd.advice)))
    }

    @Test
    fun getAdviceShouldReturnDefaultMessageWhenDatabaseIsEmpty() = runBlocking {
        val retrievedAdvice = repository.getAdvice()

        assertThat(retrievedAdvice.advice, `is`(equalTo("Uncle doesn't have any advice for you")))
    }
}