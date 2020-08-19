package com.yszln.advancedui.view.radarscanning

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
 * @description: 仿微信雷达扫描附件好友
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

    /**
     * 绘制圆的画笔
     */
    private var mCirclePaint = Paint()

    /**
     * 绘制扫描的画笔
     */
    private var mScanPaint = Paint()

    /**
     * 中心点-x
     */
    private var mCenterX = 0f;

    /**
     * 中心点-y
     */
    private var mCenterY = 0f;

    /**
     * 圆的最大半径
     */
    private var mCircleRadius = 0f;

    /**
     * 中间的图片
     */
    private var mBitmap: Bitmap? = null

    /**
     * 扫描旋转的角度
     */
    private var mRoteDegree = 0f;

    /**
     * 用于旋转的矩阵
     */
    private var mRoteMatrix = matrix


    private var mRun = object : Runnable {
        override fun run() {
            mRoteDegree += 2
            mRoteMatrix.postRotate(mRoteDegree, mCenterX, mCenterY)
            post(this)
            invalidate()
        }
    }

    init {
        mCirclePaint.isAntiAlias = true
        mCirclePaint.color = Color.LTGRAY
        mCirclePaint.style = Paint.Style.STROKE

        mScanPaint.isAntiAlias = true
        mScanPaint.color = Color.LTGRAY

        mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)
        //开始扫描
        mRun.run()


    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制圆
        drawCircle(canvas)
        canvas.save()
        //绘制扫描
        drawScan(canvas)
        canvas.restore()
        //绘制中间的图片
        drawBitmap(canvas)
    }

    private fun drawScan(canvas: Canvas) {
        /**
         * 设置扫描渐变
         */
        mScanPaint.shader = SweepGradient(
            mCenterX,
            mCenterY,
            intArrayOf(Color.TRANSPARENT, Color.parseColor("#84B5CA")),
            null
        )
        canvas.concat(mRoteMatrix)
        /**
         * 绘制扫描的圆
         */
        canvas.drawCircle(
            mCenterX, mCenterY,
            (mCircleRadius * circleProportion[4] - Math.PI).toFloat(), mScanPaint
        );
    }

    /**
     * 绘制中间的图片
     */
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

        for (fl in circleProportion) {
            canvas.drawCircle(
                mCenterX,
                mCenterY,
                mCircleRadius * fl,
                mCirclePaint
            )
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mCenterX = min(measuredWidth, measuredHeight) / 2f
        mCenterY = mCenterX
        mCircleRadius = mCenterX

    }


}