package com.yszln.advancedui.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * 6个圆旋转聚合效果，展开动画
 */
class SplashView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mDistance: Float=0f

    /**
     * 小圆的半径
     */
    private val mCircleRadius = 18f;

    /**
     * 旋转大圆的半径
     */
    private val mRotateRadius = 150f;

    /**
     * 大圆的半径
     */
    private var mCurrentRotateRadius = mRotateRadius;

    /**
     * 当前大圆旋转的角度
     */
    private var mCurrentRotateAngle = 0f;

    /**
     * 扩散圆的半径
     */
    private var mCurrentHoleRadius = 0f;

    /**
     * 动画时长
     */
    private val mRotateDuration = 500L;

    private var mColors: IntArray = IntArray(6);

    private var mX = 0f;
    private var mY = 0f;

    private var mItemAngle = 0f;

    private var mPaint = Paint()
    private var mHolePaint = Paint()

    private var onAnimatorEndListener:OnAnimatorEndListener?=null;

    init {
        mColors[0] = Color.CYAN;
        mColors[1] = Color.BLUE;
        mColors[2] = Color.GRAY;
        mColors[3] = Color.YELLOW;
        mColors[4] = Color.DKGRAY;
        mColors[5] = Color.RED;

        mItemAngle = (Math.PI * 2f / mColors.size).toFloat();

        mPaint.isAntiAlias = true
        mHolePaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mHolePaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f
        mHolePaint.strokeWidth = 5f

        mHolePaint.color = Color.WHITE


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //记录圆心位置
        mX = measuredWidth / 2f;
        mY = measuredHeight / 2f;
        mDistance= sqrt(mX*mX+mY*mY).toFloat()
    }


    private var mState: SplashState? = null
    override fun onDraw(canvas: Canvas) {
        if (mState == null) {
            mState = RotateState();
        }
        mState?.drawState(canvas)


    }

    abstract class SplashState {
        abstract fun drawState(canvas: Canvas)
    }


    private lateinit var mValueAnimator: ValueAnimator

    //1.旋转
    inner class RotateState : SplashState() {
        init {
            mValueAnimator = ValueAnimator.ofFloat(0f, (Math.PI * 2f).toFloat());
//            mValueAnimator.repeatCount = 2;
            mValueAnimator.duration = (mRotateDuration*1.5).toLong();
            mValueAnimator.interpolator = LinearInterpolator()
            mValueAnimator.addUpdateListener {
                mCurrentRotateAngle = it.animatedValue as Float
                invalidate()
            }
            mValueAnimator.start()
            mValueAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    mState = MerginState()
                }
            });
        }

        override fun drawState(canvas: Canvas) {
            //绘制背景
            drawBackground(canvas);
            //绘制小球
            drawCircle(canvas)
        }


    }

    //扩散聚合
    inner class MerginState : SplashState {

        constructor() {
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mRotateRadius);
            mValueAnimator.duration = mRotateDuration;
            mValueAnimator.interpolator = OvershootInterpolator(10f)
            mValueAnimator.addUpdateListener {
                mCurrentRotateRadius = it.animatedValue as Float
                invalidate()
            }
            mValueAnimator.reverse()
            mValueAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    mState = ExpandState()
                }
            });
        }

        override fun drawState(canvas: Canvas) {
            drawBackground(canvas)
            drawCircle(canvas)
        }

    }

    inner class ExpandState : SplashState {

        constructor() : super() {
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mDistance);
            mValueAnimator.duration = mRotateDuration * 2;
            mValueAnimator.interpolator = LinearInterpolator()
            mValueAnimator.addUpdateListener {
                mCurrentHoleRadius = it.animatedValue as Float
                invalidate()
            }
            mValueAnimator.addListener(object :AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    onAnimatorEndListener?.onEnd()
                }
            })
            mValueAnimator.start()
        }

        override fun drawState(canvas: Canvas) {
            drawBackground(canvas)
        }
    }

    private fun drawBackground(canvas: Canvas) {
        if (mCurrentHoleRadius > 0) {
            //绘制空心圆
            val strokeWidth = mDistance - mCurrentHoleRadius;
            val radius = strokeWidth / 2 + mCurrentHoleRadius;
            mHolePaint.strokeWidth = strokeWidth;
            canvas.drawCircle(mX, mY, radius, mHolePaint)
        } else {
            canvas.drawColor(Color.WHITE)
        }
    }

    private fun drawCircle(canvas: Canvas) {
        //绘制n个圆
        for (i in mColors.indices) {
            val mAngle = mItemAngle * i + mCurrentRotateAngle;
            val offsetX = mCurrentRotateRadius * sin(mAngle);
            val offsetY = mCurrentRotateRadius * cos(mAngle);
            mPaint.color = mColors[i]
            canvas.drawCircle(mX + offsetX, mY + offsetY, mCircleRadius, mPaint)

        }
    }

   interface OnAnimatorEndListener{
       fun onEnd()
   }

    public fun setOnAnimatorEndListener(onAnimatorEndListener: OnAnimatorEndListener){
        this.onAnimatorEndListener=onAnimatorEndListener;
    }


}