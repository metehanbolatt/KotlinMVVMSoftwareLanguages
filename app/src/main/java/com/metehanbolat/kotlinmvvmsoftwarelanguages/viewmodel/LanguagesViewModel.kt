package com.metehanbolat.kotlinmvvmsoftwarelanguages.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.metehanbolat.kotlinmvvmsoftwarelanguages.model.Languages
import com.metehanbolat.kotlinmvvmsoftwarelanguages.service.LanguagesAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LanguagesViewModel: ViewModel() {

    private val languagesApiService = LanguagesAPIService()
    private val disposable = CompositeDisposable()

    val languages = MutableLiveData<List<Languages>>()
    val languagesError = MutableLiveData<Boolean>()
    val languagesLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromAPI()

    }

    private fun getDataFromAPI(){
        languagesLoading.value = true

        disposable.add(
            languagesApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Languages>>(){
                    override fun onSuccess(t: List<Languages>) {
                        languages.value = t
                        languagesError.value = false
                        languagesLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        languagesLoading.value = false
                        languagesError.value = true
                        e.printStackTrace()
                    }

                })
        )

    }
}