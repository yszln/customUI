package com.yszln.advancedui.splash

import com.yszln.advancedui.R
import com.yszln.advancedui.main.BaseActivity
import com.yszln.advancedui.main.MainActivity
import kotlinx.android.synthetic.main.activity_paint.*

class SplashActivity : BaseActivity() {


    override fun getLayoutId(): Int {
        closeTitle()
        return R.layout.activity_paint
    }

    override fun initView() {
        splashView.setOnAnimatorEndListener(object : SplashView.OnAnimatorEndListener {
            override fun onEnd() {
                finish()
                gotoActivity(MainActivity::class.java)
            }
        })
    }
}