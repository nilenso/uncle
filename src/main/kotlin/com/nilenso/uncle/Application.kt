package com.nilenso.uncle

import com.nilenso.uncle.components.DaggerUncleApp

fun main(args: Array<String>) {
    val app = DaggerUncleApp.create()

    app.flyway().migrate()

    app.server().run()
}



