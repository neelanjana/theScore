package com.thescore.nbateamviewer.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.thescore.nbateamviewer.R

fun FragmentActivity.showFragment(
    fragment: Fragment,
    @IdRes container: Int,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.beginTransaction().apply {
        if (addToBackStack) {
            addToBackStack(fragment.tag)
        }
    }
        .replace(container, fragment)
        .commitAllowingStateLoss()
}

fun FragmentActivity.pushFragment(fragment: Fragment) {
    showFragment(fragment, R.id.fragment_container, true)
}


fun FragmentActivity.goBack() {
    supportFragmentManager.popBackStack()
}

fun snackBar(message: String, rootView: View) =
    Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
