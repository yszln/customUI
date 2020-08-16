package com.yszln.advancedui.view.title

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.yszln.advancedui.R
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
    FrameLayout(context, attrs, defStyleAttr), View.OnClickListener {



    init {
        inflate(context, R.layout.layout_title, this)
        title_black.setOnClickListener(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), 200)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.title_black -> {
                if (context is Activity) {
                    (context as Activity).finish()
                }
            }
            else -> {
            }
        }
    }


}