package com.kirei.biblioapp.data.utils

class ErrorHelper : Throwable {
    private var errorStatus: ERROR_STATUS? = null
    var message: String? = null

    constructor(errorStatus: ERROR_STATUS?, message: String?) {
        this.errorStatus = errorStatus
        this.message = message
    }

    fun getErrorStatus(): ERROR_STATUS? {
        return errorStatus
    }

    constructor(errorStatus: ERROR_STATUS?) {
        this.errorStatus = errorStatus
    }

    constructor(message: String?) {
        this.message = message
    }

    override fun getMessage(): String {
        return message!!
    }

    fun setMessage(message: String?) {
        this.message = message
    }
}

