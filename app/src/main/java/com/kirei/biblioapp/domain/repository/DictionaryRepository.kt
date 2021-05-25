package com.kirei.biblioapp.domain.repository

import com.kirei.biblioapp.data.response.BaseResponse
import com.kirei.biblioapp.data.datasource.DictionaryDataSource
import com.kirei.biblioapp.data.response.Results


class DictionaryRepository(private val dictionaryDataSource: DictionaryDataSource) {


    suspend fun definition(word: String): Results<BaseResponse> {
        return dictionaryDataSource.definition(word)
    }

}