package com.nilenso.uncle.webserver.modules

import com.nilenso.uncle.webserver.repositories.AdviceRepository
import com.nilenso.uncle.webserver.repositories.PostgresAdviceRepository
import dagger.Binds
import dagger.Module

@Module
interface AdviceRepositoryModule {
    @Binds fun bindAdviceRepository(postgresAdviceRepository: com.nilenso.uncle.webserver.repositories.PostgresAdviceRepository): AdviceRepository
}