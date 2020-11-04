package com.thescore.nbateamviewer.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

fun ViewGroup?.inflate(
    layoutId: Int,
    attachToRoot: Boolean = false,
    context: Context? = null
): View {
    return LayoutInflater.from(this?.context ?: context).inflate(layoutId, this, attachToRoot)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}



