package com.kirei.biblioapp.domain.usecases

import com.kirei.biblioapp.data.response.BaseResponse
import com.kirei.biblioapp.domain.repository.DictionaryRepository
import com.kirei.biblioapp.data.response.Results

class DictionaryUseCase(private val dictionaryRepository: DictionaryRepository) {
    suspend operator fun invoke(word: String): Results<BaseResponse> {
        return dictionaryRepository.definition(word)
    }
}