package com.yszln.advancedui.view.progress

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.yszln.advancedui.main.DisplayUtil

class SprotsProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val mPaint = Paint(Paint.FAKE_BOLD_TEXT_FLAG)
    private var mWidth = 0f
    private var mAnimator: ValueAnimator
    private var mStartAngle = -90f
    private var sweepAngle = 270f

    init {
//        gravity = Gravity.CENTER
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = DisplayUtil.dip2px(10f).toFloat()
        mPaint.strokeCap = Paint.Cap.ROUND
        text = "加载中..."

        mAnimator = ObjectAnimator.ofFloat(0f, 1f)
        mAnimator.duration = 500
        mAnimator.addUpdateListener {
            val schedule = it.animatedValue as Float
            sweepAngle = 360f*schedule+mStartAngle
            invalidate()
        }
//        mAnimator.interpolator = AccelerateDecelerateInterpolator()
        mAnimator.repeatCount = ValueAnimator.INFINITE
        postDelayed({
            mAnimator.start()
        }, 500)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

    }

    var mTextWidth = 0f
    var textRect = Rect()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth / 2f
        mTextWidth = paint.measureText(text.toString())
        paint.getTextBounds(
            text.toString(),
            0,
            text.length,
            textRect
        )
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText(
            text.toString(),
            mWidth / 2 - mTextWidth / 2,
            (mWidth - (textRect.top + textRect.bottom)) / 2,
            paint
        )
        mPaint.color = Color.GREEN
        canvas.drawRoundRect(
            0f,
            0f,
            mWidth,
            mWidth,
            mWidth / 2,
            mWidth / 2,
            mPaint
        )
        mPaint.color = Color.YELLOW

        canvas.drawArc(
            0f,
            0f,
            mWidth,
            mWidth,
            mStartAngle,
            sweepAngle,
            false,
            mPaint

        )
    }

    override fun onDetachedFromWindow() {
        mAnimator.cancel()
        super.onDetachedFromWindow()

    }
}