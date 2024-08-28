import com.example.module
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun getAdviceReturnsOneAdvice() = testApplication {
        application {
            module()
        }

        val response = client.get("/advice")
        val body =  response.bodyAsText()

        assertEquals(HttpStatusCode.OK, response.status)
        assert(body.isNotEmpty())
    }

}