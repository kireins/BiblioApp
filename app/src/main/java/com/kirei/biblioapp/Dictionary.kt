package com.kirei.biblioapp

import android.app.Application
import androidx.room.Room
import com.kirei.biblioapp.data.datasource.DictionaryDataSource
import com.kirei.biblioapp.domain.repository.DictionaryLocalRepository
import com.kirei.biblioapp.domain.repository.DictionaryRepository
import com.kirei.biblioapp.data.room.DictionaryDB
import com.kirei.biblioapp.data.services.Api
import com.kirei.biblioapp.data.services.ApiCreator
import com.kirei.biblioapp.data.services.ApiCreator.get
import com.kirei.biblioapp.presentation.viewmodels.DictionaryViewModel
import com.kirei.biblioapp.presentation.viewmodels.DictionaryViewModelFactory
import com.kirei.biblioapp.domain.usecases.DictionaryLocalUseCase
import com.kirei.biblioapp.domain.usecases.DictionaryUseCase
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication

class Dictionary : Application(){


    private val module = applicationContext {
        bean { ApiCreator.get(Api::class.java) }
        bean { CoroutinesContextProvider() }
        bean { DictionaryDataSource(get()) }
        bean { DictionaryRepository(get()) }
        bean { DictionaryUseCase(get()) }
        bean { DictionaryViewModelFactory(get(), get(), get()) }

        //build room database
        bean {
            Room.databaseBuilder(androidApplication(), DictionaryDB::class.java, "word_db")
                .build()
        }

        bean { get<DictionaryDB>().getWordDao() }
        bean { DictionaryLocalRepository(get()) }
        bean { DictionaryLocalUseCase(get()) }

        viewModel { DictionaryViewModel(get(), get(), get()) }


    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(module))
    }

}