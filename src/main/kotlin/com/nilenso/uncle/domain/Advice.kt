package com.nilenso.uncle.domain

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object AdviceTable : IntIdTable("advice") {
    val adviceText = varchar("advice_text", 1024)
}

class Advice(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Advice>(AdviceTable)

    var advice by AdviceTable.adviceText
}