package com.thescore.nbateamviewer.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.thescore.nbateamviewer.ui.base.BaseActivity
import com.thescore.nbateamviewer.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startAnimation()
    }

    private fun startAnimation() {
        val anim: Animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        anim.reset()
        anim.duration = 1500
        tv_title.clearAnimation()
        tv_title.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }

}