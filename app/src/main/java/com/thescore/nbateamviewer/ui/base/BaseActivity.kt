package com.thescore.nbateamviewer.ui.base

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.thescore.nbateamviewer.extensions.*

abstract class BaseActivity : AppCompatActivity() {


    fun showLoading(progressBar: ProgressBar) = progressBar?.visible()

    fun hideLoading(progressBar: ProgressBar) = progressBar?.gone()

    fun showError(errorMessage: String?, rootView: View) =
        snackBar(errorMessage ?: "Error", rootView)

    fun addFragment(fragment: Fragment, containerId: Int, addToBackStack: Boolean = false) {
        showFragment(fragment, containerId, addToBackStack)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) finish() else goBack()
    }
}
