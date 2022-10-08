package com.argel.fakecelebrityvoices.core.presentation

import com.argel.fakecelebrityvoices.core.exception.Failure

interface OnFailure {
    fun handleFailure(failure: Failure?)
}