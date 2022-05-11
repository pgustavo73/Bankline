package com.santanderdevweek.bankline.domain

data class AccountItem(
    val conta: Conta,
    val cpf: String,
    val id: Int,
    val nome: String
)