package com.yszln.advancedui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yszln.advancedui.R
import com.yszln.advancedui.ball.BallView
import com.yszln.advancedui.splash.SplashView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
//        setContentView(SplashView(this))
    }
}