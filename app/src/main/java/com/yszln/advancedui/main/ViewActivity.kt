package com.yszln.advancedui.main

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.yszln.advancedui.R
import kotlinx.android.synthetic.main.activity_view.*

class ViewActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_view

    override fun initView() {
        titleBarView?.mTitleTv?.text = intent?.getStringExtra("title")
        val stringExtra = intent.getStringExtra("clazz")
        val loadClass = Class.forName(stringExtra)
        val constructor = loadClass.getConstructor(Context::class.java)
        val newInstance = constructor.newInstance(this) as View
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        newInstance.layoutParams = params


        rootLayout.addView(newInstance)

    }
}