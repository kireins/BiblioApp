package com.kirei.biblioapp.domain.usecases

import com.kirei.biblioapp.domain.repository.DictionaryLocalRepository
import com.kirei.biblioapp.data.room.entity.WordEntity

class DictionaryLocalUseCase(private val dictionaryLocalRepository: DictionaryLocalRepository){


    // method to get all words from the Word Entity
    suspend fun getAllWords(): List<WordEntity>{
        return  dictionaryLocalRepository.getAllWordEntries()
    }

}