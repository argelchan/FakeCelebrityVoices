package com.argel.fakecelebrityvoices.core.presentation

abstract class BaseViewState {
    object ShowLoading : BaseViewState()
    object HideLoading : BaseViewState()
    object ShowPlaceHolder : BaseViewState()
    object HidePlaceHolder : BaseViewState()
}