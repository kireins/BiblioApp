package com.kirei.biblioapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirei.biblioapp.data.utils.ErrorHelper


open class MainViewModel : ViewModel(){

    val errorBase = MutableLiveData<ErrorHelper>()
    val progress = MutableLiveData<Boolean>()


    fun progress(): MutableLiveData<Boolean> {
        return progress
    }

    override fun onCleared() {
        super.onCleared()

    }

}
