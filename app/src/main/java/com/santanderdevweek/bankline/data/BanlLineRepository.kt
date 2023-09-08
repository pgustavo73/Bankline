package com.santanderdevweek.bankline.data

import android.util.Log
import androidx.lifecycle.liveData
import com.santanderdevweek.bankline.domain.AccountItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BanlLineRepository {

    private val TAG = javaClass.simpleName

    const val BASE_URL = "https://bankline-api-production.up.railway.app"

    private val restApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BanklineApi::class.java)
    }

    fun findBankStatement(accountHolderId: Int) = liveData {
        emit(State.Wait)
        try {
            emit(State.Success(data = restApi.findBankStatement(accountHolderId)))
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }

    }
}
