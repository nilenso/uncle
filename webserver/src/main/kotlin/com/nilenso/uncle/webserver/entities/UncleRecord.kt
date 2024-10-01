package com.nilenso.uncle.webserver.entities

import org.jetbrains.exposed.dao.id.IntIdTable

object NapTable: IntIdTable("nap") {
    val status = integer(name = "status")
    val uncleId = integer(name = "uncle_id")
        .uniqueIndex()
        .references(UncleTable.id)
}

object UncleTable : IntIdTable("uncle") {
    val status = integer(name = "status")
    val napId = integer(name = "nap_id")
        .uniqueIndex()
        .references(NapTable.id)
}

