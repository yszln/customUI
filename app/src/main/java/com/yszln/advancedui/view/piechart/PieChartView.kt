package com.yszln.advancedui.view.piechart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.yszln.advancedui.main.DisplayUtil
import kotlin.math.cos
import kotlin.math.sin

class PieChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val RADIUS = DisplayUtil.dip2px(100f).toFloat()
    private val LENGTH = DisplayUtil.dip2px(10f).toFloat()
    var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var rect = RectF()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect.set(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.color = Color.YELLOW
        canvas.drawArc(rect, 0f, 60f, true, mPaint)
        mPaint.color = Color.RED
        canvas.drawArc(rect, 60f, 130f, true, mPaint)

        //将扇形偏移出来
        canvas.save()
        canvas.translate(
            cos(Math.toRadians((190f + 20f / 2f).toDouble()) * LENGTH).toFloat()
            , sin(Math.toRadians((190f + 20f / 2f).toDouble()) * LENGTH).toFloat()
        )
        mPaint.color = Color.GREEN
        canvas.drawArc(rect, 190f, 20f, true, mPaint)
        canvas.restore()


        mPaint.color = Color.BLUE
        canvas.drawArc(rect, 210f, 30f, true, mPaint)
        mPaint.color = Color.BLACK
        canvas.drawArc(rect, 240f, 20f, true, mPaint)
        mPaint.color = Color.GRAY
        canvas.drawArc(rect, 260f, 20f, true, mPaint)
        mPaint.color = Color.LTGRAY
        canvas.drawArc(rect, 280f, 80f, true, mPaint)
    }

}