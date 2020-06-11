package com.yszln.advancedui.view.title

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.yszln.advancedui.R

/**
 * @fileName: TitleBar
 * @author: huwei
 * @date: 2020/6/11 16:36
 * @description: 标题栏
 * @history:
 */
class TitleBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context,R.layout.layout_title,this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),200)
    }


}