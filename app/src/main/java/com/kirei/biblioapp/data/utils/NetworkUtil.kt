package com.kirei.biblioapp.data.utils

import java.io.IOException
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.kirei.biblioapp.presentation.ui.activities.DefinitionActivity
import com.kirei.biblioapp.data.response.Results
import com.kirei.biblioapp.data.response.Word
import retrofit2.HttpException

suspend fun <T : Any> safeApiCall(call: suspend () -> Results<T>): Results<T> {


    return try {
        call()
    } catch (e: Exception) {
        when (e) {
            is IOException -> {
                Results.Error(ErrorHelper(ERROR_STATUS.NETWORK))
            }
            is HttpException -> {
                Results.Error(ErrorHelper(ERROR_STATUS.UNKNOWN))
            }
            else -> {
                Results.Error(ErrorHelper(ERROR_STATUS.ERR, e.message))
            }
        }
    }
}

val <T> T.exhaustive: T
    get() = this


// live data observer
fun <D : Any, T : LiveData<D>> T.observeWith(owner: LifecycleOwner, receiver: (D) -> Unit) {
    observe(owner, Observer<D> {
        if (it != null) {
            receiver(it)
        }
    })
}

// the enum for error status, also note enum classes only allow a single instance of each value and can not encode
//more information on each type
enum class ERROR_STATUS {
    NETWORK,
    UNKNOWN,
    ERR
}

fun Context.Intent1(word: Word): Intent {
    return Intent(this, DefinitionActivity::class.java).apply {
        val wordJsonBody = Gson().toJson(word)
        putExtra(Constants.INTENT_WORD_JSON, wordJsonBody)
    }
}