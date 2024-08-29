package com.example.modules

import com.example.repositories.AdviceRepository
import com.example.repositories.PostgresAdviceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface AdviceRepositoryModule {
    @Binds fun bindAdviceRepository(postgresAdviceRepository: PostgresAdviceRepository): AdviceRepository
}