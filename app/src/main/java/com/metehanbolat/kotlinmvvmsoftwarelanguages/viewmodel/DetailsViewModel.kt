package com.metehanbolat.kotlinmvvmsoftwarelanguages.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.metehanbolat.kotlinmvvmsoftwarelanguages.model.Languages

class DetailsViewModel: ViewModel() {

    val languagesLiveData = MutableLiveData<Languages>()

    fun getDataFromRoom(){
        val language = Languages("Python","Python is the best programming language.","www.ss.com")
        languagesLiveData.value = language
    }
}