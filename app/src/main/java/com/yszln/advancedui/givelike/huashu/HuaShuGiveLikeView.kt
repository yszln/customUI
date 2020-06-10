package com.yszln.advancedui.givelike.huashu

import android.animation.*
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import com.yszln.advancedui.R
import java.util.*
import kotlin.collections.ArrayList

/** Copyright (C), 汀沐科技
 * @fileName: GiveLikeHeartView
 * @author: huwei
 * @date: 2020/6/9 16:22
 * @description: 仿花束点赞效果
 * @history:
 */
class HuaShuGiveLikeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {


    /**
     * 绘制的宽度
     */
    private var mWidth = 0;

    /**
     * 绘制的高度
     */
    private var mHeight = 0;

    /**
     * 图标列表
     */
    private var mHeartList = ArrayList<Int>();

    /**
     * 随即数
     */
    private var mRandom = Random();

    /**
     * 飘动的图标大小
     */
    private var mDrawWith = 60;

    /**
     * 飘动的图标大小
     */
    private var mDrawHeight = 60;

    /**
     * 起点
     */
    private var mStartPointF: PointF? = null;

    /**
     * 终点
     */
    private var mEndPointF: PointF? = null;

    /**
     * 动画时间
     */
    private var mDuration = 3000L

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
        if (null == mStartPointF) {
            //起始位置
            mStartPointF = PointF((mWidth - mDrawWith) / 2f, mHeight.toFloat())

        }
    }


    public fun start() {
        //开启执行动画
        for (i in 0..2)
            for (res in mHeartList) {
                val imageView = ImageView(context)
                imageView.layoutParams = ViewGroup.LayoutParams(mDrawWith, mDrawHeight)
                imageView.setImageResource(res);
                val animatorSet = getAnimatorSet(imageView)
                addView(imageView)
                animatorSet.start()
            }

    }

    /**
     *设置动画
     */
    private fun getAnimatorSet(imageView: ImageView): AnimatorSet {
        //缩放动画
        val scaleYAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 0.3f, 1f)
        val scaleXAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 0.3f, 1f)

        //透明度动画
        val alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f)
        val animatorSet = AnimatorSet()
        val bezierAnimator = getBezierAnimator(imageView)
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator, bezierAnimator)
        animatorSet.duration = mDuration
        animatorSet.setTarget(imageView)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                //动画结束剔除当前view
                removeView(imageView);
            }
        });
        return animatorSet
    }

    /**
     * 每个图标移动的轨迹
     */
    private fun getBezierAnimator(imageView: ImageView): ValueAnimator {
        //曲线的两个小顶点,在范围内随机生成
        val point1 = PointF(getRandomWidth(), getRandomHeight())
        val point2 = PointF(getRandomWidth(), getRandomHeight())

        //随机结束位置
        mEndPointF = PointF(getRandomWidth(), 0f)


        val bezierEvaluator = BezierEvaluator(point1, point2)

        val valueAnimator = ValueAnimator.ofObject(bezierEvaluator, mStartPointF, mEndPointF)

        val nextInt = mRandom.nextInt(mInterpolatorList.size)

        valueAnimator.interpolator = mInterpolatorList[nextInt]
        valueAnimator.setTarget(imageView)
        valueAnimator.addUpdateListener {
            val pointf = it.animatedValue as PointF
            Log.e(imageView.hashCode().toString(), "x->${pointf.x},y->${pointf.y}")
            imageView.x = pointf.x
            imageView.y = pointf.y
            imageView.alpha = 1 - it.animatedFraction + 0.1f;// 得到百分比
        }


        return valueAnimator;
    }


    /**
     * 生成区间内随机横坐标
     */
    private fun getRandomWidth(): Float {
//       占控件1/3
//        val max = mWidth - mDrawWith / 2
//        val min = mWidth / 3 - mDrawWith / 2
//        return (mRandom.nextInt(max - min) + min).toFloat()
        //占满控件
        return (mRandom.nextInt(mWidth - mDrawWith / 2)).toFloat()
    }

    /**
     * 生成区间内随机纵坐标
     */
    private fun getRandomHeight(): Float {
        val max = mHeight / 3 * 2 - mDrawHeight / 2
        val min = mHeight / 3 - mDrawHeight / 2
        return (mRandom.nextInt(max - min) + min).toFloat()
    }

}