package com.nilenso.uncle.webserver

import com.nilenso.uncle.webserver.components.DaggerUncleApp

fun main(args: Array<String>) {
    val app = DaggerUncleApp.create()

    app.flyway().migrate()

    app.server().run()
}



