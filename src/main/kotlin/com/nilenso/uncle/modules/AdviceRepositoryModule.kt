package com.nilenso.uncle.modules

import com.nilenso.uncle.repositories.AdviceRepository
import com.nilenso.uncle.repositories.PostgresAdviceRepository
import dagger.Binds
import dagger.Module

@Module
interface AdviceRepositoryModule {
    @Binds fun bindAdviceRepository(postgresAdviceRepository: PostgresAdviceRepository): AdviceRepository
}