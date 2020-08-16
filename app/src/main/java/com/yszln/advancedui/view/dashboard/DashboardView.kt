package com.yszln.advancedui.view.dashboard

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.yszln.advancedui.main.DisplayUtil
import kotlin.math.cos
import kotlin.math.sin

class DashboardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /**
     * 圆弧的半径
     */
    private val RADIUS = DisplayUtil.dip2px(150f)
    private val ANGLE = 120f
    private val LENGTH = RADIUS/4*3

    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var dashEffect: PathDashPathEffect
    private val dashPath = Path()

    init {
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = DisplayUtil.dip2px(2f).toFloat()


        dashPath.addRect(
            0f,
            0f,
            DisplayUtil.dip2px(2f).toFloat(),
            DisplayUtil.dip2px(10f).toFloat(),
            Path.Direction.CW
        )

        var arc=Path()
        arc.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90 + ANGLE / 2,
            360 - ANGLE)
        val pathMeasure = PathMeasure(arc, false)

        val advance = (pathMeasure.length-DisplayUtil.dip2px(2f).toFloat())/20f
        dashEffect = PathDashPathEffect(
            dashPath,
            advance,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画圆弧
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90 + ANGLE / 2,
            360 - ANGLE,
            false,
            mPaint
        )
        //画刻度
        mPaint.pathEffect = dashEffect
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90 + ANGLE / 2,
            360 - ANGLE,
            false,
            mPaint
        )
        mPaint.pathEffect = null
        //画文字

        //画指针
        val curr = getAngleFromMark(5).toDouble()
        canvas.drawLine(
            width / 2f,
            height / 2f,
            (cos(Math.toRadians(curr)) * LENGTH+width/2f).toFloat(),
            (sin(Math.toRadians(curr)) * LENGTH+height/2f).toFloat(),
            mPaint
        )
    }

    private fun getAngleFromMark(mark: Int) =
        (90f + ANGLE / 2f + (360f - ANGLE) / 20f * mark)

}