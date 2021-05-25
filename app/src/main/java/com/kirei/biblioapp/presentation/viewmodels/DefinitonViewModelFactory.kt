package com.kirei.biblioapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kirei.biblioapp.CoroutinesContextProvider
import com.kirei.biblioapp.domain.usecases.DictionaryLocalUseCase
import com.kirei.biblioapp.domain.usecases.DictionaryUseCase


class DictionaryViewModelFactory (private val dictionaryUseCase: DictionaryUseCase,
                                  private val dictionaryLocalUseCase: DictionaryLocalUseCase,
                                  private val coroutinesContextProvider: CoroutinesContextProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DictionaryViewModel(dictionaryUseCase, dictionaryLocalUseCase, coroutinesContextProvider) as T
    }

}