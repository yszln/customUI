package com.yszln.advancedui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.yszln.advancedui.view.title.TitleBarView

/**
 * @fileName: BaseActivity
 * @author: huwei
 * @date: 2020/6/11 15:33
 * @description:
 * @history:
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = window.decorView.findViewById<ViewGroup>(android.R.id.content)
        content?.addView(TitleBarView(this))
        setContentView(getLayoutId())
        initView()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()

    fun gotoActivity(clazz: Class<*>?) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

}