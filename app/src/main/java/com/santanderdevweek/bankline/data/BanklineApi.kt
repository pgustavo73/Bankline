package com.santanderdevweek.bankline.data

import com.santanderdevweek.bankline.domain.AccountItem
import com.santanderdevweek.bankline.domain.Movimentacao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BanklineApi {

    @GET("movimentacoes/{id}")
    suspend fun findBankStatement(@Path("id") accountHolderId: Int): List<Movimentacao>

    @GET("/correntistas")
    fun findAccountsHolder() : Call<List<AccountItem>>
}