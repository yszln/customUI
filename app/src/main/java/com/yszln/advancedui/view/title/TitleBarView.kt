package com.yszln.advancedui.view.title

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yszln.advancedui.R
import com.yszln.advancedui.utils.StatusBarUtil
import kotlinx.android.synthetic.main.layout_title.view.*

/**
 * @fileName: TitleBar
 * @author: huwei
 * @date: 2020/6/11 16:36
 * @description: 标题栏
 * @history:
 */
class TitleBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {
    var mBlackIv: View
    var mTitleTv: TextView
    var mMoreIv: View

    fun setTitle(title: CharSequence?) {
        titleTitle.text = title
    }
    init {
        View.inflate(context, R.layout.layout_title, this)
        mBlackIv = titleBlack
        mTitleTv = titleTitle
        mMoreIv = titleMore
        titleBlack.setOnClickListener {
            if (context is Activity) {
                context.finish()
            }
        }

        if (context is AppCompatActivity) {
            //设置标题
            setTitle(context.title)
            //设置沉浸式状态栏
            StatusBarUtil.immersive(context)
            StatusBarUtil.darkMode(context)
            StatusBarUtil.setPaddingSmart(context, this)
        }
    }


}