package com.nilenso.uncle.webserver.components

import com.nilenso.uncle.webserver.modules.TestDatabaseModule
import com.nilenso.uncle.webserver.repositories.PostgresAdviceRepository
import dagger.Component
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import javax.inject.Singleton

@Component(modules = [TestDatabaseModule::class])
@Singleton
interface TestComponent {
    fun getPostgresAdviceRepository(): PostgresAdviceRepository

    class Extension: ParameterResolver {
        override fun supportsParameter(
            parameterContext: ParameterContext?,
            extensionContext: ExtensionContext?
        ): Boolean {
            return parameterContext?.parameter?.type == TestComponent::class.java
        }

        override fun resolveParameter(parameterContext: ParameterContext?, extensionContext: ExtensionContext?): Any {
            return DaggerTestComponent.create()
        }
    }
}