package com.yszln.advancedui.radarscanning

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.yszln.advancedui.R
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
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /**
     * 绘制的圆的比例
     */
    private val circleProportion =
        floatArrayOf(1 / 13f, 2 / 13f, 3 / 13f, 4 / 13f, 5 / 13f, 6 / 13f)

    private var mPaint = Paint()

    private var mWidth = 0;
    private var mHeight = 0;
    private var mCenterX = 0f;
    private var mCenterY = 0f;
    private var mCircleRadius = 0f;
    private var mBitmap: Bitmap? = null

    /**
     * 扫描旋转的角度
     */
    private var mRoteDegree = 0f;

    private var mRoteMatrix = Matrix()


    private var mRun = object : Runnable {
        override fun run() {
            mRoteDegree += 2
            mRoteMatrix.postRotate(mRoteDegree)
            postDelayed(this, 60)
            invalidate()
        }
    }

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.LTGRAY
        mPaint.style = Paint.Style.STROKE
        mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        mRun.run()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        drawCircle(canvas)
        drawBitmap(canvas)
        canvas.restore()
    }

    private fun drawBitmap(canvas: Canvas) {
        mBitmap?.let {
            canvas.drawBitmap(
                it,
                mCenterX - it.width / 2,
                mCenterY - it.height / 2,
                null
            )
        }
    }

    /**z
     * 绘制6个圆
     */
    private fun drawCircle(canvas: Canvas) {
        mPaint.shader = SweepGradient(
            mCenterX,
            mCenterY,
            intArrayOf(Color.TRANSPARENT, Color.parseColor("#84B5CA")),
            null
        )
//        canvas.concat(mRoteMatrix)
        for (fl in circleProportion) {
            canvas.drawCircle(
                mCenterX,
                mCenterY,
                mCircleRadius * fl,
                mPaint
            )
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
        mCenterX = min(measuredWidth, measuredHeight) / 2f
        mCenterY = mCenterX
        mCircleRadius = mCenterX


    }


}