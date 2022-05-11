package com.santanderdevweek.bankline.data

import java.util.*

enum class Currency(val locale: Locale) {
    BRL(Locale("pt", "BR")),
    ;

    companion object {
        fun getByName(name: String) = values().find { it.name == name } ?: BRL
    }
}