package com.metehanbolat.kotlinmvvmsoftwarelanguages.service

import com.metehanbolat.kotlinmvvmsoftwarelanguages.model.Languages
import io.reactivex.Single
import retrofit2.http.GET

interface LanguagesAPI {

    //https://raw.githubusercontent.com/metehanie/LanguagesJsonFiles/main/data.json

    @GET("metehanie/LanguagesJsonFiles/main/data.json")
    fun getLanguages(): Single<List<Languages>>
}