package com.yszln.advancedui.view.special

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 *471*558
 */
class DoraemonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val mPaint=Paint()

    init {
        setBackgroundColor(Color.WHITE)
        mPaint.color=Color.BLACK
        mPaint.isAntiAlias=true
        mPaint.strokeWidth=2f
        mPaint.style=Paint.Style.STROKE
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画头的外圈
        canvas.drawArc(79f,0f,471f-44f,298f,135f,270f,false,mPaint)
        mPaint.color=Color.YELLOW
        canvas.drawArc(90f,53f,471f-59f,301f,135f,270f,false,mPaint)

    }
}