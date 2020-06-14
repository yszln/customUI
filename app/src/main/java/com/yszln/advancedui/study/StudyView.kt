package com.yszln.advancedui.study

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class StudyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private var mPaint = Paint()
    private var mPath = Path()

    init {
        mPaint.style = Paint.Style.STROKE
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 4f
        mPaint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制三阶贝塞尔曲线
        mPath.moveTo(300f, 500f)
        mPath.cubicTo(500f, 100f, 600f, 1200f, 800f, 500f)
        //相对位置，等同上面的代码
        //mPath.rCubicTo(200f, -400f, 300f, 700f, 500f, 0f)

        canvas.drawPath(mPath, mPaint)

    }
}