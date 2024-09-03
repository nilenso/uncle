package com.nilenso.uncle.webserver.plugins

import com.nilenso.uncle.webserver.dao.AdviceDAO
import com.nilenso.uncle.webserver.handlers.AdviceHandler
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.serialization.json.Json
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class RoutingTest {

    @Test
    fun getHealthShouldReturnOK() = testApplication {
        val mockAdviceHandler = mockk<AdviceHandler>()

        application {
            configureSerialization()
            configureRouting(mockAdviceHandler)
        }

        client.get("/health").apply {
            assertThat(status, `is`(equalTo(HttpStatusCode.OK)))
            assertThat(bodyAsText(), `is`(equalTo("OK")))
        }
    }

    @Test
    fun getAdviceShouldReturnAdvice() = testApplication {
        val mockAdviceHandler = mockk<AdviceHandler>()
        val expectedAdvice = AdviceDAO("Sage advice")
        coEvery { mockAdviceHandler.getAdvice() } returns expectedAdvice

        application {
            configureSerialization()
            configureRouting(mockAdviceHandler)
        }

        client.get("/advice").apply {
            assertThat(status, `is`(equalTo(HttpStatusCode.OK)))
            assertThat(bodyAsText(), `is`(equalTo(Json.encodeToString(AdviceDAO.serializer(), expectedAdvice))))
        }

        coVerify { mockAdviceHandler.getAdvice() }
    }

    @Test
    fun postAdviceShouldCallAddAdvice() = testApplication {
        val mockAdviceHandler = mockk<AdviceHandler>()
        val newAdvice = AdviceDAO("New advice")
        coJustRun { mockAdviceHandler.addAdvice(any()) }

        application {
            configureSerialization()
            configureRouting(mockAdviceHandler)
        }

        client.post("/advice") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(AdviceDAO.serializer(), newAdvice))
        }.apply {
            assertThat(status, `is`(equalTo(HttpStatusCode.NoContent)))
        }

        coVerify { mockAdviceHandler.addAdvice(newAdvice) }
    }

    @Test
    fun postAdviceShouldReturn400ForEmptyAdvice() = testApplication {
        val mockAdviceHandler = mockk<AdviceHandler>()
        val emptyAdvice = AdviceDAO("")

        application {
            configureSerialization()
            configureRouting(mockAdviceHandler)
        }

        client.post("/advice") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(AdviceDAO.serializer(), emptyAdvice))
        }.apply {
            assertThat(status, `is`(equalTo(HttpStatusCode.BadRequest)))
        }

        coVerify(exactly = 0) { mockAdviceHandler.addAdvice(any()) }
    }
}