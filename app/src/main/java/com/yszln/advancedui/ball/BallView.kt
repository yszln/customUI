package com.yszln.advancedui.ball

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import com.yszln.advancedui.R

/**
 * 粒子爆炸效果
 */
class BallView : View {

    var mPaint: Paint = Paint();
    lateinit var mBitmap: Bitmap;
    var mBalls = ArrayList<Ball>()
    val d = 3f;//粒子直径
    var mValueAnimator: ValueAnimator


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mBitmap = BitmapFactory.decodeResource(
            context.resources,
            R.mipmap.xjj
        )
        mBitmap.width
        mBitmap.height

        for (i in 0 until mBitmap.width/3) {
            for (j in 0 until mBitmap.height/3) {
                val ball = Ball()
                ball.color = mBitmap.getPixel(i*3, j*3)
                ball.x = i * d + d / 2
                ball.y = j * d + d / 2
                ball.r = d / 2
                mBalls.add(ball)
            }
        }
        mValueAnimator = ValueAnimator.ofFloat(0f, 1f)
        mValueAnimator.interpolator = LinearInterpolator()
        mValueAnimator.duration = 2000
        mValueAnimator.addUpdateListener {
            updateBall()
            invalidate()
        }
    }

    private fun updateBall() {
        for (mBall in mBalls) {
            mBall.x += mBall.vx + mBall.ax;
            mBall.y += mBall.vy + mBall.ay;

            mBall.ax += (mBall.ax + getRadomx())
            mBall.ay += mBall.ay + getRadomy()
        }
    }

    private fun getRadomx(): Float {
        val random = (Math.random() * 40f).toFloat()
        val d1 = (random % 2).toInt()
        return if (d1 == 0) -random else random;
    }

    private fun getRadomy(): Float {
        val random = Math.random()
        return (random * 80f).toFloat();
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            mValueAnimator.start()
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (mBall in mBalls) {
            mPaint.color = mBall.color
            canvas.drawCircle(mBall.x, mBall.y, d, mPaint)
        }
    }
}