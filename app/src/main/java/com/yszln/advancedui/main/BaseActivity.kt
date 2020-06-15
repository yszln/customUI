package com.yszln.advancedui.main

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
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

    /**
     * 是否添加标题栏
     */
    var isAddTitle = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val resId = getLayoutId()
        if (isAddTitle) {
            val linearLayout = LinearLayout(this)
            linearLayout.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT
            )
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.addView(TitleBarView(this))
            linearLayout.addView(LayoutInflater.from(this).inflate(resId, null, false))
            setContentView(linearLayout)
        } else {
            setContentView(resId)
        }

        initView()
    }

    protected fun closeTitle(){
        isAddTitle=false
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()

    fun gotoActivity(clazz: Class<*>?) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

}