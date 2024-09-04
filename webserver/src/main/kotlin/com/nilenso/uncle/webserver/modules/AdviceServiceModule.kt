package com.nilenso.uncle.webserver.modules

import com.nilenso.uncle.webserver.services.AdviceServiceImpl
import com.nilenso.uncle.webserver.services.AdviceService
import dagger.Binds
import dagger.Module

@Module
interface AdviceServiceModule {
    @Binds fun bindAdviceService(adviceService: AdviceServiceImpl): AdviceService
}