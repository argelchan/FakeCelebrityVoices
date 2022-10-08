package com.argel.fakecelebrityvoices.core.exception

import androidx.annotation.StringRes
import com.argel.fakecelebrityvoices.R

abstract class Failure {
    data class ServerError(val code: Int, val serverMessage: String?, val payload: String? = null) :
        Failure()
    object NetworkConnection : FeatureFailure(R.string.failure_no_internet)
    abstract class FeatureFailure(@StringRes val defaultMessage: Int = R.string.failure_no_internet) : Failure()
}