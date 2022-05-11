package com.santanderdevweek.bankline.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {

    companion object {

        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BanlLineRepository.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}