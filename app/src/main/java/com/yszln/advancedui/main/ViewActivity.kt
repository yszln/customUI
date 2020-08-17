package com.yszln.advancedui.main

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val stringExtra = intent.getStringExtra("clazz")
        val loadClass = Class.forName(stringExtra)
        val constructor = loadClass.getConstructor(Context::class.java)
        val newInstance = constructor.newInstance(this) as View
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        params.gravity = Gravity.CENTER
        newInstance.layoutParams = params


        setContentView(newInstance)
    }
}