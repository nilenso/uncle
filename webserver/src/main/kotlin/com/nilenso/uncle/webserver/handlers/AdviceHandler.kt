package com.nilenso.uncle.webserver.handlers

import io.ktor.server.application.*

interface AdviceHandler {
   suspend fun getAdvice(call: ApplicationCall)
   suspend fun addAdvice(call: ApplicationCall)
   suspend fun goNap(call: ApplicationCall)
   suspend fun goWakeup(call: ApplicationCall)
}
