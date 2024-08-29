package com.example

import com.example.components.DaggerUncleApp

fun main(args: Array<String>) {
    val app = DaggerUncleApp.create()

    app.flyway().migrate()

    app.server().run()
}



