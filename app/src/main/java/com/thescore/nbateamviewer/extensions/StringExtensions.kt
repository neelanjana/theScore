package com.thescore.nbateamviewer.extensions

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan

fun String.makeBold() : SpannableStringBuilder{
    val str = SpannableStringBuilder(this)
    str.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        length-1,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return str
}