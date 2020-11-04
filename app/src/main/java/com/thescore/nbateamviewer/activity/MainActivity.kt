package com.thescore.nbateamviewer.activity

import android.os.Bundle
import com.thescore.nbateamviewer.R
import com.thescore.nbateamviewer.extensions.showFragment
import com.thescore.nbateamviewer.ui.base.BaseActivity
import com.thescore.nbateamviewer.ui.home.view.HomeListFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.showFragment(
            HomeListFragment(),
            R.id.fragment_container, true
        )
    }
}