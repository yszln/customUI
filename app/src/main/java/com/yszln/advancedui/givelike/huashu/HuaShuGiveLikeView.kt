package com.yszln.advancedui.givelike.huashu

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.ImageView
import android.widget.RelativeLayout
import com.yszln.advancedui.R
import java.util.*
import kotlin.collections.ArrayList

/** Copyright (C), 汀沐科技
 * @fileName: GiveLikeHeartView
 * @author: huwei
 * @date: 2020/6/9 16:22
 * @description: 点赞效果
 * @history:
 */
class HuaShuGiveLikeView : RelativeLayout {


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        gravity = Gravity.BOTTOM

    }

    private var mWidth = 0;
    private var mHeight = 0;
    private var mHeartList = ArrayList<Int>();
    private var mRandom = Random();

    private var mInterpolatorList = ArrayList<Interpolator>()

    init {
        mHeartList.add(R.mipmap.heart1)
        mHeartList.add(R.mipmap.heart2)
        mHeartList.add(R.mipmap.heart3)
        mHeartList.add(R.mipmap.heart4)
        mHeartList.add(R.mipmap.heart5)
        mHeartList.add(R.mipmap.heart6)
        mHeartList.add(R.mipmap.heart7)

        mInterpolatorList.add(LinearInterpolator())//线性
        mInterpolatorList.add(AccelerateDecelerateInterpolator())//先加速后减速
        mInterpolatorList.add(AccelerateInterpolator())//加速
        mInterpolatorList.add(DecelerateInterpolator())//减速
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
    }


    public fun start() {
        //开启执行动画
        for (res in mHeartList) {
            val imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(80, 80)
            imageView.setImageResource(res);
            val animatorSet = getAnimatorSet(imageView)
            addView(imageView)
            animatorSet.start()
        }

    }

    fun getAnimatorSet(imageView: ImageView): AnimatorSet {
        //缩放动画
        val scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 0.3f, 1f)
        val scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 0.3f, 1f)

        //透明度动画
        val alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f)
        val animatorSet = AnimatorSet()
        val bezierAnimator = getBezierAnimator(imageView)
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator, bezierAnimator)
        animatorSet.duration = 5000
        animatorSet.setTarget(imageView)
        return animatorSet
    }

    fun getBezierAnimator(imageView: ImageView): ValueAnimator {
        //曲线的两个小顶点
        val point1 = PointF((mWidth / 2 - 200).toFloat(), mHeight.toFloat() / 2)
        val point2 = PointF((mWidth / 2 + 200).toFloat(), mHeight.toFloat() / 4)
        //起始位置
        val startPoint = PointF((mWidth / 2).toFloat(), 0f)
        //结束位置
        val endPoint = PointF(mRandom.nextInt(mWidth / 2).toFloat(), 0f)

        val bezierEvaluator = BezierEvaluator(point1, point2)
        val valueAnimator = ValueAnimator.ofObject(bezierEvaluator, startPoint, endPoint)
        valueAnimator.addUpdateListener {
            val pointf = it.animatedValue as PointF
            imageView.x = pointf.x
            imageView.y = pointf.y
            imageView.alpha = 1 - it.animatedFraction + 0.1f;// 得到百分比
        }
        valueAnimator.interpolator = mInterpolatorList[mRandom.nextInt(mInterpolatorList.size)]
        valueAnimator.setTarget(imageView)
        return valueAnimator;
    }


}