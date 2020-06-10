package com.yszln.advancedui.radarscanning

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 * @fileName: WXRadarScanningView
 * @author: huwei
 * @date: 2020/6/10 16:49
 * @description: 防微信雷达扫描附件好友
 * @history:
 */
class WXRadarScanningView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0
) : View(context, attrs, defStyleAttr) {

    /**
     * 绘制的圆的比例
     */
    private val circleProportion =
        floatArrayOf(1 / 13f, 2 / 13f, 3 / 13f, 4 / 13f, 5 / 13f, 6 / 13f)

    private var mPaint = Paint()

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.LTGRAY
        mPaint.style = Paint.Style.STROKE
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)

    }

    /**
     * 绘制6个圆
     */
    private fun drawCircle(canvas: Canvas) {

        for (fl in circleProportion) {
            canvas.drawCircle(
                measuredWidth / 2f,
                measuredHeight / 2f,
                min(measuredWidth, measuredHeight).toFloat()/2 * fl,
                mPaint
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


}