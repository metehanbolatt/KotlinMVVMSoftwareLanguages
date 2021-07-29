package com.metehanbolat.kotlinmvvmsoftwarelanguages.service

import com.metehanbolat.kotlinmvvmsoftwarelanguages.model.Languages
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LanguagesAPIService {

    //https://raw.githubusercontent.com/metehanie/LanguagesJsonFiles/main/data.json

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(LanguagesAPI::class.java)

    fun getData(): Single<List<Languages>> {
        return api.getLanguages()
    }
}