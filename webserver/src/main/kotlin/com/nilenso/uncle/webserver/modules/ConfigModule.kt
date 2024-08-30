package com.nilenso.uncle.webserver.modules

import com.nilenso.uncle.webserver.config.UncleConfig
import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface ConfigModule {

    companion object {
        @Provides
        @Singleton
        fun providesUncleConfig(): UncleConfig {
            return ConfigLoaderBuilder.default()
                .addResourceSource("/application.yaml")
                .build()
                .loadConfigOrThrow<UncleConfig>()
        }
    }
}