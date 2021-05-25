package com.kirei.biblioapp.data.datasource

import com.kirei.biblioapp.data.response.BaseResponse
import com.kirei.biblioapp.data.services.Api
import com.kirei.biblioapp.data.response.Results
import com.kirei.biblioapp.data.utils.ERROR_STATUS
import com.kirei.biblioapp.data.utils.ErrorHelper
import com.kirei.biblioapp.data.utils.safeApiCall

class DictionaryDataSource(private val api: Api){
    suspend fun  definition(word: String) = safeApiCall(
        call ={getDefinition(word)}
    )

    // get definition
    private suspend fun getDefinition(word: String): Results<BaseResponse> {
        api.getDefinitionAsync(word).await().let {
            if (!it.whenEmpty()) {
                return Results.Success(it)
            }
            return Results.Error(ErrorHelper(ERROR_STATUS.ERR))
        }
    }


}