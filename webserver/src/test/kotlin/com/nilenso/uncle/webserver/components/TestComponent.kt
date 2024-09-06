package com.nilenso.uncle.webserver.components

import com.nilenso.uncle.webserver.modules.TestDatabaseModule
import com.nilenso.uncle.webserver.repositories.PostgresAdviceRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [TestDatabaseModule::class])
@Singleton
interface TestComponent {
    fun getPostgresAdviceRepository(): PostgresAdviceRepository
}