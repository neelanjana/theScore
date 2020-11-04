package com.thescore.nbateamviewer.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {


    override fun onCleared() {
        super.onCleared()
    }

    open fun handleError(throwable: Throwable) {
    }

}
