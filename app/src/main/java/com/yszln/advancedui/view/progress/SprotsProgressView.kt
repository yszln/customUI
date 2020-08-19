package com.yszln.advancedui.view.progress

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.yszln.advancedui.main.DisplayUtil

class SprotsProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val mPaint = Paint(Paint.FAKE_BOLD_TEXT_FLAG)
    private var mWidth = 0f
    private var mAnimator: ValueAnimator
    private var mStartAngle = -90f
    private var sweepAngle = 360f

    private val mBorderWidth= DisplayUtil.dip2px(10f).toFloat();

    init {
        gravity = Gravity.CENTER
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth =mBorderWidth
        mPaint.strokeCap = Paint.Cap.ROUND
        text = "加载中..."

        mAnimator = ObjectAnimator.ofFloat(0f, 1f)
        mAnimator.duration = 5000
        mAnimator.addUpdateListener {
            val schedule = it.animatedValue as Float
            sweepAngle = 360f*schedule
            invalidate()
        }
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
    var fontMetrics=Paint.FontMetrics()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth / 2f
        mTextWidth = paint.measureText(text.toString())
        //计算基线
        paint.getTextBounds(
            text.toString(),
            0,
            text.length,
            textRect
        )
        //另一种获取基线,与文字无关的方式
        paint.getFontMetrics(fontMetrics)
    }

    override fun onDraw(canvas: Canvas) {

//        val offset=textRect.top + textRect.bottom;
        val offset=fontMetrics.ascent+fontMetrics.descent;
        canvas.drawText(
            text.toString(),
            mWidth / 2 - mTextWidth / 2,
            (mWidth - offset)/ 2,
            paint
        )
        mPaint.color = Color.GREEN
        canvas.drawRoundRect(
            mBorderWidth+paddingLeft,
            mBorderWidth+paddingTop,
            mWidth-paddingRight,
            mWidth-paddingBottom,
            mWidth / 2,
            mWidth / 2,
            mPaint
        )
        mPaint.color = Color.BLUE

        canvas.drawArc(
            mBorderWidth+paddingLeft,
            mBorderWidth+paddingTop,
            mWidth-paddingRight,
            mWidth-paddingBottom,
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